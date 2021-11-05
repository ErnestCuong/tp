package tutoraid.logic.commands;

import static java.util.Objects.requireNonNull;
import static tutoraid.model.Model.PREDICATE_SHOW_ALL_LESSONS;

import java.util.List;

import tutoraid.commons.core.Messages;
import tutoraid.commons.core.index.Index;
import tutoraid.logic.commands.exceptions.CommandException;
import tutoraid.model.Model;
import tutoraid.model.lesson.Lesson;
import tutoraid.model.student.Progress;
import tutoraid.model.student.Student;

/**
 * Adds progress string to an exiting student in TutorAid. Updates the progress if one already exists.
 */
public class AddProgressCommand extends AddCommand {

    public static final String COMMAND_FLAG = "-p";

    public static final String MESSAGE_USAGE = COMMAND_FLAG + ": Adds a progress for a student in TutorAid identified "
            + "by the index number used in the last student listing. "
            + "Existing progress will be overwritten by the input.\n"
            + "Parameters: STUDENT_INDEX (must be a positive integer) "
            + "PROGRESS\n"
            + "Example: " + COMMAND_FLAG + " 1 "
            + "Finishes Prelims.";

    public static final String MESSAGE_SUCCESS = "Added progress: %1$s\nFor this student: %2$s";

    private final Index targetIndex;
    private final Progress progress;

    /**
     * @param targetIndex of the student in the filtered student list to add progress
     * @param progress    Progress object to be added to the student
     */
    public AddProgressCommand(Index targetIndex, Progress progress) {
        this.targetIndex = targetIndex;
        this.progress = progress;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Student> lastShownStudentList = model.getFilteredStudentList();

        model.updateFilteredLessonList(PREDICATE_SHOW_ALL_LESSONS);
        List<Lesson> lessonList = model.getFilteredLessonList();

        if (targetIndex.getZeroBased() >= lastShownStudentList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
        }

        Student studentToEdit = lastShownStudentList.get(targetIndex.getZeroBased());
        Lesson.updateStudentLessonLink(lessonList, studentToEdit, studentToEdit);

        studentToEdit.addProgress(this.progress);

        model.viewStudent(studentToEdit);

        return new CommandResult(String.format(MESSAGE_SUCCESS, progress, studentToEdit));
    }
}
