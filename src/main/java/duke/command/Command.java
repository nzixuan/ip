package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public interface Command {

    void execute(TaskList task, Ui ui, Storage storage) throws DukeException;
    boolean isExit();

}