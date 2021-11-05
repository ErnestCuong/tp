package tutoraid.model.lesson;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;

import tutoraid.model.lesson.exceptions.DuplicateStudentInLessonException;
import tutoraid.model.lesson.exceptions.StudentNotFoundInLessonException;
import tutoraid.model.student.Student;
import tutoraid.model.student.StudentName;

/**
 * Represents a lesson's list of names of students that attend this lesson
 */
public class Students {

    public final ArrayList<StudentName> students;

    /**
     * Constructs a {@code Students}.
     *
     * @param students Valid arraylist of StudentName objects.
     */
    public Students(ArrayList<StudentName> students) {
        this.students = students;
    }

    /**
     * Checks if this list has the name of a student.
     */
    public boolean hasStudent(Student student) {
        requireNonNull(student);
        return students.contains(student.getStudentName());
    }

    /**
     * Adds the name of a student to this list.
     *
     * @param student the student whose name is to be added
     * @throws DuplicateStudentInLessonException if this student's name is already in the list
     */
    public void addStudent(Student student) {
        requireNonNull(student);
        StudentName studentNameToAdd = student.getStudentName();
        if (students.contains(studentNameToAdd)) {
            throw new DuplicateStudentInLessonException();
        }
        students.add(studentNameToAdd);
    }

    /**
     * Removes the name of a student from this list.
     *
     * @param student the student whose name is to be removed from this list
     * @throws StudentNotFoundInLessonException if this student's name is not in the list
     */
    public void removeStudent(Student student) {
        requireNonNull(student);
        StudentName studentNameToRemove = student.getStudentName();
        if (!students.contains(studentNameToRemove)) {
            throw new StudentNotFoundInLessonException();
        }
        students.remove(studentNameToRemove);
    }

    /**
     * Returns a string Array that contains all the student names in the correct order.
     */
    public ArrayList<String> getAllStudentNamesAsStringArrayList() {
        ArrayList<String> allStudentNamesAsStringArrayList = new ArrayList<>();
        for (StudentName studentName : students) {
            String currentLessonName = studentName.toString();
            allStudentNamesAsStringArrayList.add(currentLessonName);
        }
        return allStudentNamesAsStringArrayList;

    }

    /**
     * Returns the number of students in this list.
     */
    public int numberOfStudents() {
        return students.size();
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        int counter = 1;

        for (StudentName studentName : students) {
            str.append("\n").append(counter).append(".  ").append(studentName);
            counter++;
        }

        if (str.toString().equals("")) {
            return "No students";
        } else {
            return str.toString();
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Students // instanceof handles nulls
                && students.equals(((Students) other).students)); // state check
    }

    @Override
    public int hashCode() {
        return students.hashCode();
    }
}
