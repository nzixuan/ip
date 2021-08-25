package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class FindCommand implements Command {
    String desc;
    public FindCommand(String desc) {
        this.desc = desc;
    }
    public void execute(TaskList task, Ui ui, Storage storage) throws DukeException {
        task.findTask(desc);
    }

    public boolean isExit() {
        return false;
    }
}