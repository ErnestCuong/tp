package tutoraid.logic.commands.util;

import tutoraid.commons.core.Messages;
import tutoraid.commons.core.index.Index;
import tutoraid.logic.commands.exceptions.CommandException;
import tutoraid.model.Model;
import tutoraid.model.lesson.Lesson;
import tutoraid.model.student.Student;

import java.util.List;

import static java.util.Objects.requireNonNull;

public class StudentLessonUtil {
    /**
     * Gets a Student object from the Model object and the specified index in the last shown student list.
     *
     * @param model the Model object to get the Student object from
     * @param studentIndex the index of the Student object in the last shown student list
     * @return the Student object that is wanted
     * @throws CommandException if the index provided is invalid
     */
    public static Student getStudent(Model model, Index studentIndex) throws CommandException {
        requireNonNull(model);
        List<Student> lastShownStudentList = model.getFilteredStudentList();
        if (studentIndex.getZeroBased() >= lastShownStudentList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
        }
        return lastShownStudentList.get(studentIndex.getZeroBased());
    }

    /**
     * Gets a Lesson object from the Model object and the specified index in the last shown lesson list.
     *
     * @param model the Model object to get the Lesson object from
     * @param lessonIndex the index of the Lesson object in the last shown lesson list
     * @return the Lesson object that is wanted
     * @throws CommandException if the index provided is invalid
     */
    public static Lesson getLesson(Model model, Index lessonIndex) throws CommandException {
        requireNonNull(model);
        List<Lesson> lastShownLessonList = model.getFilteredLessonList();
        if (lessonIndex.getZeroBased() >= lastShownLessonList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_LESSON_DISPLAYED_INDEX);
        }
        return lastShownLessonList.get(lessonIndex.getZeroBased());
    }

    /**
     * Updates the link between students and lessons because restarting TutorAid disconnects them.
     *
     * @param model the Model for TutorAid
     * @param student the student that we want to update the link
     * @param lesson the lesson that we want to update the link
     */
    public static void updateStudentAndLessonLinks(Model model, Student student, Lesson lesson) {
        model.updateFilteredLessonList(Model.PREDICATE_SHOW_ALL_LESSONS);
        model.updateFilteredStudentList(Model.PREDICATE_SHOW_ALL_STUDENTS);
        List<Student> studentList = model.getFilteredStudentList();
        List<Lesson> lessonList = model.getFilteredLessonList();
        Lesson.updateStudentLessonLink(lessonList, student, student);
        Student.updateStudentLessonLink(studentList, lesson, lesson);
    }
}
