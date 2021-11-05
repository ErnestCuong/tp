package tutoraid.model.student;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;

import tutoraid.commons.util.AppUtil;
import tutoraid.model.lesson.Lesson;
import tutoraid.model.lesson.LessonName;
import tutoraid.model.student.exceptions.DuplicateStudentLessonsException;
import tutoraid.model.student.exceptions.LessonNotFoundInStudentException;

/**
 * Represents a student's list of names of attended lessons in TutorAid.
 */
public class Lessons {

    public static final String MESSAGE_CONSTRAINTS =
            "Lessons constructor either takes in no argument "
                    + "or takes in a String ArrayList";

    public final ArrayList<LessonName> lessons;

    /**
     * Constructs a {@code Lessons}.
     */
    public Lessons() {
        lessons = new ArrayList<>();
    }

    /**
     * Constructs a {@code Lessons} with existing lesson names.
     *
     * @param lessonNames an arraylist of strings that are valid lesson names
     */
    public Lessons(ArrayList<String> lessonNames) {
        requireNonNull(lessonNames);
        AppUtil.checkArgument(isValidLessonNames(lessonNames), MESSAGE_CONSTRAINTS);

        lessons = new ArrayList<>();

        for (String lessonName : lessonNames) {
            LessonName currentLessonName = new LessonName(lessonName);
            lessons.add(currentLessonName);
        }
    }

    /**
     * Checks if a given String ArrayList is a valid list of lesson names.
     *
     * @param lessonNames an ArrayList of strings
     * @return true if all elements are valid lesson names, false otherwise
     */
    public static boolean isValidLessonNames(ArrayList<String> lessonNames) {
        requireNonNull(lessonNames);
        for (String lessonName : lessonNames) {
            if (lessonName == null || !LessonName.isValidLessonName(lessonName)) {
                return false;
            }
        }

        return true;
    }

    /**
     * Adds the name of a lesson to this list.
     *
     * @param toAdd the lesson whose name is to be added
     * @throws DuplicateStudentLessonsException if the lesson's name is already in the list
     */
    public void addLesson(Lesson toAdd) {
        requireNonNull(toAdd);
        LessonName lessonNameToAdd = toAdd.getLessonName();
        if (lessons.contains(lessonNameToAdd)) {
            throw new DuplicateStudentLessonsException();
        }
        lessons.add(lessonNameToAdd);
    }

    /**
     * Removes the name of a lesson from this list.
     *
     * @param toDelete the lesson whose name is to be removed from this list
     * @throws LessonNotFoundInStudentException if the lesson's name is not in the list
     */
    public void removeLesson(Lesson toDelete) {
        requireNonNull(toDelete);
        LessonName lessonNameToDelete = toDelete.getLessonName();
        if (!lessons.contains(lessonNameToDelete)) {
            throw new LessonNotFoundInStudentException();
        }
        lessons.remove(lessonNameToDelete);
    }

    /**
     * Checks if this list has the name of a lesson.
     */
    public boolean hasLesson(Lesson lesson) {
        requireNonNull(lesson);
        return lessons.contains(lesson.getLessonName());
    }

    /**
     * Returns a String Array that contains all the lesson names in the correct order.
     */
    public ArrayList<String> getAllLessonNamesAsStringArrayList() {
        ArrayList<String> allLessonNamesAsStringArrayList = new ArrayList<>();
        for (LessonName lesson : lessons) {
            String currentLessonName = lesson.toString();
            allLessonNamesAsStringArrayList.add(currentLessonName);
        }
        return allLessonNamesAsStringArrayList;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        int counter = 1;

        for (String lessonName : getAllLessonNamesAsStringArrayList()) {
            str.append("\n").append(counter).append(".  ").append(lessonName);
            counter++;
        }

        if (str.toString().equals("")) {
            return "No Lessons";
        } else {
            return str.toString();
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Lessons // instanceof handles nulls
                && this.lessons.equals(((Lessons) other).lessons)); // state check
    }

    @Override
    public int hashCode() {
        return lessons.hashCode();
    }
}
