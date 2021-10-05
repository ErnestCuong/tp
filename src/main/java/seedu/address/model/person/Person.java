package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.tag.Tag;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {

    // Identity fields
    private final StudentName studentName;
    private final Phone studentPhone;
    private final ParentName parentName;
    private final Phone parentPhone;

    /**
     * Every field must be present and not null.
     */
    public Person(StudentName studentName, Phone studentPhone, ParentName parentName, Phone parentPhone) {
        requireAllNonNull(studentName, studentPhone, parentName, parentPhone);
        this.studentName = studentName;
        this.studentPhone = studentPhone;
        this.parentName = parentName;
        this.parentPhone = parentPhone;
    }

    public StudentName getStudentName() {
        return studentName;
    }

    public Phone getStudentPhone() {
        return studentPhone;
    }

    public ParentName getParentName() {
        return parentName;
    }

    public Phone getParentPhone() {
        return parentPhone;
    }

    /**
     * Returns true if both persons have the same name.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null
                && otherPerson.getStudentName().equals(getStudentName());
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Person)) {
            return false;
        }

        Person otherPerson = (Person) other;
        return otherPerson.getStudentName().equals(getStudentName())
                && otherPerson.getStudentPhone().equals(getStudentPhone())
                && otherPerson.getParentName().equals(getParentName())
                && otherPerson.getParentPhone().equals(getParentPhone());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(studentName, studentPhone, parentName, parentPhone);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getStudentName());

        if (studentPhone != null) {
            builder.append("; Student's phone: ")
                    .append(getStudentPhone());
        }

        if (parentName != null) {
            builder.append("; Parent's name: ")
                    .append(getParentName());
        }

        if (parentPhone != null) {
            builder.append("; Parent's phone: ")
                    .append(getParentPhone());
        }

        return builder.toString();
    }

}
