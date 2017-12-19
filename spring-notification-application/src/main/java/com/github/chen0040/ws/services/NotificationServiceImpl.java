package com.github.chen0040.ws.services;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.github.chen0040.ws.models.AuditEvent;
import com.github.chen0040.ws.models.DutyOfficer;
import com.github.chen0040.ws.models.DutyShift;
import com.github.chen0040.ws.models.LogicalGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private LogicalGroupService logicalGroupService;

    @Autowired
    private DutyShiftService dutyShiftService;

    @Autowired
    private DutyOfficerService dutyOfficerService;

    private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    private Map<Long, String> getServiceNames(List<LogicalGroup> groups) {
        Map<Long, String> result = new HashMap<>();
        for (LogicalGroup group : groups) {
            result.put(group.getId(), group.getGroupName());
        }
        return result;
    }


    private List<Long> getShiftIds(List<DutyShift> affectedShifts) {
        Set<Long> result = new HashSet<>();
        for (DutyShift shift : affectedShifts) {
            result.add(shift.getId());
        }
        return new ArrayList<>(result);
    }



    private List<Long> getLogicalGroupIds(List<LogicalGroup> affectedGroups) {
        Set<Long> results = new HashSet<>();

        for (LogicalGroup group : affectedGroups) {
            results.add(group.getId());
        }

        return new ArrayList<>(results);

    }


    private List<DutyOfficer> getDutyOfficersFromIds(List<DutyOfficer> officers, Set<Long> affectedDutyOfficerIds) {
        List<DutyOfficer> result = new ArrayList<>();
        for (DutyOfficer officer : officers) {
            if (affectedDutyOfficerIds.contains(officer.getId())) {
                result.add(officer);
            }
        }

        return result;
    }


    private Set<Long> getAffectedDutyOfficerIds(List<DutyShift> affectedShifts) {
        Set<Long> affected = new HashSet<>();
        for (DutyShift shift : affectedShifts) {
            affected.addAll(shift.getDutyOfficersInScope());
        }
        return affected;
    }


    private List<DutyShift> getAffectedShifts(final List<DutyShift> shifts, final List<LogicalGroup> affectedGroups, AuditEvent alarm) {
        List<DutyShift> affectedShifts = new ArrayList<>();

        for (DutyShift shift : shifts) {
            boolean containsAffectedService = false;
            for (LogicalGroup group : affectedGroups) {
                if (shift.getLogicalGroupsInScope().contains(group.getId())) {
                    containsAffectedService = true;
                    break;
                }
            }
            if (!containsAffectedService)
                continue;

            long activeDateTime = alarm.getTime().getTime();

            if (shift.inSchedule(activeDateTime)) {
                affectedShifts.add(shift);
            }
        }

        return affectedShifts;
    }


    private List<LogicalGroup> getAffectedServices(final List<LogicalGroup> groups, AuditEvent alarm) {

        List<LogicalGroup> filtered = new ArrayList<>();
        for (LogicalGroup group : groups) {
            if (group.match(alarm)) {
                filtered.add(group);
            }
        }

        return filtered;
    }

    @Override
    public AuditEvent process(AuditEvent alarm) {
        final List<LogicalGroup> groups = logicalGroupService.findAll();
        final List<DutyShift> shifts = dutyShiftService.findAll();
        final List<DutyOfficer> officers = dutyOfficerService.findAll();

        final List<LogicalGroup> affectedGroups = getAffectedServices(groups, alarm);
        final Map<Long, String> affectedGroupNames = getServiceNames(affectedGroups);
        final List<DutyShift> affectedShifts = getAffectedShifts(shifts, affectedGroups, alarm);
        final Set<Long> affectedDutyOfficerIds = getAffectedDutyOfficerIds(affectedShifts);
        final List<Long> affectedShiftIds = getShiftIds(affectedShifts);
        final List<DutyOfficer> affectedDutyOfficers = getDutyOfficersFromIds(officers, affectedDutyOfficerIds);

        alarm.getImpacts().put("+(DATE) spark stream processing time", dateFormat.format(new Date()));

        alarm.getImpacts().put("+(JSON) duty officers to notify", JSON.toJSONString(affectedDutyOfficers, SerializerFeature.BrowserCompatible));
        alarm.getImpacts().put("+(JSON) services impacted", JSON.toJSONString(affectedGroupNames, SerializerFeature.BrowserCompatible));
        alarm.getImpacts().put("+(ID) shifts to notify", JSON.toJSONString(affectedShiftIds, SerializerFeature.BrowserCompatible));

        return alarm;
    }
}
