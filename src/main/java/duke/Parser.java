package duke;

import duke.command.*;

public class Parser {


    enum Action {
        DONE,
        DELETE,
        TODO,
        DEADLINE,
        EVENT,
    }

    public static Command parse(String str) throws DukeException {

            String[] arr = str.split(" ", 2);
            if (arr.length < 1) {
                throw new DukeException("No command was given.");
            }
            String firstWord = arr[0];
            if (str.equalsIgnoreCase("bye")) {
                return new ExitCommand();
            } else if (str.equalsIgnoreCase("list")) {
                return new ListCommand();
            } else {
                Action action = parseFirstWord(firstWord.toLowerCase());

                if (arr.length < 2 && (action == Action.DONE || action == Action.DELETE)) {
                    throw new DukeException("duke.task.Task number for "+firstWord+" is not given.");
                } else if (arr.length < 2 || arr[1].isBlank() ) {
                    throw new DukeException("The description of " + firstWord + " cannot be empty");
                } else {
                    return actionToCommand(action, arr[1]);
                }
            }
    }

    private static Action parseFirstWord(String firstWord) throws DukeException {
        switch (firstWord) {
            case "done":
                return Action.DONE;
            case "delete":
                return Action.DELETE;
            case "todo":
                return Action.TODO;
            case "deadline":
                return Action.DEADLINE;
            case "event":
                return Action.EVENT;
            default:
                throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }
    }


    private static Command actionToCommand(Action action, String str) throws DukeException {
        switch (action) {
            case DONE:
                return new DoneCommand(str);
            case DELETE:
                return new DeleteCommand(str);
            case TODO:
                return new AddToDoCommand(str);
            case DEADLINE:
                return new AddDeadlineCommand(str);
            case EVENT:
                return new AddEventCommand(str);
            default:
                throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }
    }


}