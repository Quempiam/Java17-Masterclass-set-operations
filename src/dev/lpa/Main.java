package dev.lpa;

import java.util.*;

public class Main {

    public static void main(String[] args) {

        Set<Task> tasks = TaskData.getTasks("all");
        sortAndPrint("Tasks from manager", tasks);

        Comparator<Task> sortByPriority = Comparator.comparing(Task::getPriority);
        Set<Task> annsTasks = TaskData.getTasks("Ann");
        sortAndPrint("Ann's Tasks", annsTasks, sortByPriority);

        Set<Task> bobsTasks = TaskData.getTasks("Bob");
        sortAndPrint("Bob's Tasks", bobsTasks);

        Set<Task> carolsTasks = TaskData.getTasks("Carol");
        sortAndPrint("Carol's Tasks", carolsTasks);

        Set<Task> bobsAndCarolsTasks = getIntersect(bobsTasks, carolsTasks);
        Set<Task> bobsAndAnnsTasks = getIntersect(bobsTasks, annsTasks);
        Set<Task> annsAndCarolsTasks = getIntersect(annsTasks, carolsTasks);

        Set<Task> allTasks = getUnion(tasks, getUnion(annsTasks, getUnion(bobsTasks, carolsTasks)));
        sortAndPrint("All Tasks", allTasks);

        Set<Task> assignedTasks = getUnion(annsTasks, getUnion(bobsTasks, carolsTasks));
        sortAndPrint("Tasks assigned to someone", allTasks);

        Set<Task> notAssignedTasks = getDifference(tasks, assignedTasks);
        sortAndPrint("Not assigned tasks", notAssignedTasks, sortByPriority);

        Set<Task> teamTasks = getUnion(bobsAndAnnsTasks, getUnion(bobsAndCarolsTasks, annsAndCarolsTasks));
        sortAndPrint("Multiple assigned tasks", teamTasks, Comparator.comparing(Task::getStatus));

    }

    private static void sortAndPrint(String header, Collection<Task> collection) {
        sortAndPrint(header, collection, null);
    }

    private static void sortAndPrint(String header, Collection<Task> collection,
                                     Comparator<Task> sorter) {

        String lineSeparator = "_".repeat(90);
        System.out.println(lineSeparator);
        System.out.println(header);
        System.out.println(lineSeparator);

        List<Task> list = new ArrayList<>(collection);
        list.sort(sorter);
        list.forEach(System.out::println);
    }

    private static <T> Set<T> getUnion(Set<T> a, Set<T> b) {
        Set<T> ret = new HashSet<>(a);
        ret.addAll(b);
        return ret;
    }

    private static <T> Set<T> getDifference (Set<T> a, Set<T> b){
        Set<T> ret = new HashSet<>(a);
        ret.removeAll(b);
        return ret;
    }

    private static <T> Set<T> getIntersect (Set<T> a, Set<T> b){
        Set<T> ret = new HashSet<>(a);
        ret.retainAll(b);
        return ret;
    }
}
