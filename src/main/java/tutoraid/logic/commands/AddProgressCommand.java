package tutoraid.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import tutoraid.commons.core.Messages;
import tutoraid.commons.core.index.Index;
import tutoraid.logic.commands.exceptions.CommandException;
import tutoraid.model.Model;
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
     * @param progress Progress object to be added to the student
     */
    public AddProgressCommand(Index targetIndex, Progress progress) {
        this.targetIndex = targetIndex;
        this.progress = progress;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Student> lastShownList = model.getFilteredStudentList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
        }

        Student studentToEdit = lastShownList.get(targetIndex.getZeroBased());
        studentToEdit.addProgress(this.progress);

        model.updateFilteredStudentList(Model.PREDICATE_SHOW_ALL_STUDENTS);
        model.updateFilteredLessonList(Model.PREDICATE_SHOW_ALL_LESSONS);
        model.viewList(true);

        return new CommandResult(String.format(MESSAGE_SUCCESS, progress, studentToEdit));
    }
}
