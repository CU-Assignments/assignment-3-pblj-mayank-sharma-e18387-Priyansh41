import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class CourseFullException extends Exception {
    public CourseFullException(String message) {
        super(message);
    }
}

class PrerequisiteNotMetException extends Exception {
    public PrerequisiteNotMetException(String message) {
        super(message);
    }
}

class UniversityEnrollmentSystem {
    private static final int MAX_STUDENTS = 3;
    private static Map<String, Integer> courseEnrollment = new HashMap<>();
    private static Map<String, String> prerequisites = new HashMap<>();
    private static Map<String, Boolean> completedCourses = new HashMap<>();

    static {
        prerequisites.put("Advanced Java", "Core Java");
        courseEnrollment.put("Advanced Java", 2);
        completedCourses.put("Core Java", false);
    }

    public static void enrollStudent(String course) throws CourseFullException, PrerequisiteNotMetException {
        if (prerequisites.containsKey(course)) {
            String prerequisite = prerequisites.get(course);
            if (!completedCourses.getOrDefault(prerequisite, false)) {
                throw new PrerequisiteNotMetException(
                        "Complete " + prerequisite + " before enrolling in " + course + ".");
            }
        }

        int enrolled = courseEnrollment.getOrDefault(course, 0);
        if (enrolled >= MAX_STUDENTS) {
            throw new CourseFullException("The course " + course + " is full.");
        }

        courseEnrollment.put(course, enrolled + 1);
        System.out.println("Successfully enrolled in " + course + ".");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enroll in Course: ");
        String course = scanner.nextLine();

        try {
            enrollStudent(course);
        } catch (CourseFullException | PrerequisiteNotMetException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}
