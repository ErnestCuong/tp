package tutoraid.logic.commands.util;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import tutoraid.commons.core.Messages;
import tutoraid.commons.core.index.Index;
import tutoraid.logic.commands.exceptions.CommandException;
import tutoraid.model.Model;
import tutoraid.model.lesson.Lesson;
import tutoraid.model.student.Student;

public class StudentLessonUtil {
    /**
     * Checks if the student indexes and the lesson indexes are valid.
     *
     * @param model the model used to check the validity of the indexes
     * @param lessonIndexes an Arraylist of Index objects of lessons
     * @param studentIndexes an Arraylist of Index objects of students
     * @throws CommandException if any index(es) are invalid
     */
    public static void checkIndexesAreValid(Model model, ArrayList<Index> lessonIndexes, ArrayList<Index> studentIndexes)
            throws CommandException {
        List<Student> lastShownStudentList = model.getFilteredStudentList();
        List<Lesson> lastShownLessonList = model.getFilteredLessonList();
        int maxStudentIndex = studentIndexes.stream().max(
                Comparator.comparingInt(Index::getZeroBased)).get().getZeroBased();
        int maxLessonIndex = lessonIndexes.stream().max(
                Comparator.comparingInt(Index::getZeroBased)).get().getZeroBased();
        if (maxStudentIndex >= lastShownStudentList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
        }
        if (maxLessonIndex >= lastShownLessonList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_LESSON_DISPLAYED_INDEX);
        }
    }

}
