package tutoraid.model.lesson;

import tutoraid.model.student.Student;

import static tutoraid.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Represents a Lesson in the TutorAid.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Lesson {

    // Identity Fields
    private final LessonName lessonName;

    // Data Fields
    private final ArrayList<String> students;
    private final Capacity capacity;
    private final Price price;
    private final Timing timing;

    /**
     * Every field must be present and not null.
     */
    public Lesson(LessonName lessonName, Capacity capacity, Price price, ArrayList<String> students, Timing timing) {
        requireAllNonNull(lessonName, capacity, price, students, timing);
        this.lessonName = lessonName;
        this.capacity = capacity;
        this.price = price;
        this.students = students;
        this.timing = timing;
    }

    public LessonName getLessonName() {
        return lessonName;
    }

    public Capacity getCapacity() {
        return capacity;
    }

    public Price getPrice() {
        return price;
    }

    public ArrayList<String> getStudents() {
        return students;
    }

    public Timing getTiming() {
        return timing;
    }

    /**
     * Returns true if both lessons have the same name.
     * This defines a weaker notion of equality between two lessons.
     */
    public boolean isSameLesson(Lesson otherLesson) {
        if (otherLesson == this) {
            return true;
        }

        return otherLesson != null
                && otherLesson.getLessonName().equals(this.getLessonName());
    }

    /**
     * Returns true if both lessons have the same identity and data fields.
     * This defines a stronger notion of equality between two lessons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Lesson)) {
            return false;
        }

        Lesson otherLesson = (Lesson) other;
        return otherLesson.getLessonName().equals(getLessonName())
                && otherLesson.getCapacity().equals(getCapacity())
                && otherLesson.getPrice().equals(getPrice())
                && otherLesson.getStudents().equals(getStudents())
                && otherLesson.getTiming().equals(getTiming());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(lessonName, timing, capacity, price, students);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();

        builder.append(getLessonName());

        if (lessonName != null) {
            builder.append("; Lesson's name: ")
                    .append(getLessonName());
        }

        if (timing != null) {
            builder.append("; Lesson's timing: ")
                    .append(getTiming());

        }

        if (capacity != null) {
            builder.append("; Lesson's capacity: ")
                    .append(getCapacity());
        }

        if (price != null) {
            builder.append("; Lesson's price: ")
                    .append(getCapacity());
        }

        if (students != null) {
            builder.append("; Lesson's students: ")
                    .append(getStudents());
        }

        return builder.toString();
    }
}
