package com.ninetwozero.bf4intel.database.dao.assignments;

import android.util.Pair;

import com.ninetwozero.bf4intel.database.dao.AbstractSorter;
import com.ninetwozero.bf4intel.database.dao.unlocks.SortMode;
import com.ninetwozero.bf4intel.json.assignments.Assignment;
import com.ninetwozero.bf4intel.json.assignments.Assignments;
import com.ninetwozero.bf4intel.json.assignments.SortedAssignmentContainer;

import java.util.*;

public class AssignmentSorter extends AbstractSorter<SortedAssignmentContainer> {
    public static final List<String> ASSIGNMENT_TYPE = new ArrayList<String>(Arrays.asList("bronze", "silver", "gold", "sp"));
    private final Assignments assignments;
    private Map<String, List<Assignment>> groupedMap = new HashMap<String, List<Assignment>>();

    public AssignmentSorter(final Assignments assignments) {
        this.assignments = assignments;
    }

    @Override
    public SortedAssignmentContainer sort(final SortMode mode) {
        if (mode == SortMode.PROGRESS) {
            return sortByProgress();
        } else {
            return sortByCategory();
        }
    }

    @Override
    protected SortedAssignmentContainer sortByProgress() {
        if (groupedMap.size() == 0) {
            sortByCategory();
        }
        List<Assignment> uncompleted = new ArrayList<Assignment>();
        List<Assignment> completed = new ArrayList<Assignment>();
        for (String group : ASSIGNMENT_TYPE) {
            Pair<List<Assignment>, List<Assignment>> pair = fetchUncompletedAssignments(group);
            uncompleted.addAll(pair.first);
            completed.addAll(pair.second);
        }
        Collections.sort(uncompleted);
        uncompleted.addAll(completed);
        return new SortedAssignmentContainer(uncompleted, assignments.getExpansions());
    }

    @Override
    protected SortedAssignmentContainer sortByCategory() {
        List<Assignment> orderedAssignments = new ArrayList<Assignment>();
        if (groupedMap.size() == 0) {
            Map<String, List<String>> missions = assignments.getAssignmentCategory();
            for (String group : ASSIGNMENT_TYPE) {
                List<String> groupedAssignments = missions.get(group);
                List<Assignment> assignmentList = fetchGroupedAssignments(groupedAssignments, group);
                groupedMap.put(group, assignmentList);
                orderedAssignments.addAll(assignmentList);
            }
        } else {
            for (String group : ASSIGNMENT_TYPE) {
                orderedAssignments.addAll(groupedMap.get(group));
            }
        }
        return new SortedAssignmentContainer(orderedAssignments, assignments.getExpansions());
    }

    private List<Assignment> fetchGroupedAssignments(final List<String> assignmentsInGroup, final String group) {
        List<Assignment> orderedGroup = new ArrayList<Assignment>();
        for (String key : assignmentsInGroup) {
            if (assignments.getAssignments().containsKey(key)) {
                Assignment assignment = assignments.getAssignments().get(key);
                assignment.setGroup(group);
                orderedGroup.add(assignment);
            }
        }
        return orderedGroup;
    }

    private Pair<List<Assignment>, List<Assignment>> fetchUncompletedAssignments(final String group) {
        List<Assignment> uncompleted = new ArrayList<Assignment>();
        List<Assignment> completed = new ArrayList<Assignment>();
        List<Assignment> assignmentsOfGroup = groupedMap.get(group);
        for (Assignment assignment : assignmentsOfGroup) {
            if (assignment.getCompletion() != 100) {
                uncompleted.add(assignment);
            } else {
                completed.add(assignment);
            }
        }
        return new Pair<List<Assignment>, List<Assignment>>(uncompleted, completed);
    }
}
