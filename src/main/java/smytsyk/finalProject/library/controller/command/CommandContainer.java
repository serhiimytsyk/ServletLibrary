package smytsyk.finalProject.library.controller.command;

import smytsyk.finalProject.library.controller.command.impl.*;
import smytsyk.finalProject.library.controller.command.impl.adminCommands.GoCommands.GoToAdminBooksPageCommand;
import smytsyk.finalProject.library.controller.command.impl.adminCommands.GoCommands.GoToAdminUsersPageCommand;
import smytsyk.finalProject.library.controller.command.impl.adminCommands.GoCommands.ReturnToAdminPageCommand;
import smytsyk.finalProject.library.controller.command.impl.adminCommands.UpdateUserAdminCommand;
import smytsyk.finalProject.library.controller.command.impl.adminCommands.UserCommands.AddLibrarianCommand;
import smytsyk.finalProject.library.controller.command.impl.adminCommands.UserCommands.BanUserCommand;
import smytsyk.finalProject.library.controller.command.impl.adminCommands.UserCommands.RemoveLibrarianCommand;
import smytsyk.finalProject.library.controller.command.impl.adminCommands.UserCommands.UnbanUserCommand;
import smytsyk.finalProject.library.controller.command.impl.adminCommands.bookCommands.AddBookCommand;
import smytsyk.finalProject.library.controller.command.impl.adminCommands.bookCommands.DeleteBookCommand;
import smytsyk.finalProject.library.controller.command.impl.adminCommands.bookCommands.UpdateBookCommand;
import smytsyk.finalProject.library.controller.command.impl.bannedCommands.ReturnToBannedPageCommand;
import smytsyk.finalProject.library.controller.command.impl.bannedCommands.UpdateUserBannedCommand;
import smytsyk.finalProject.library.controller.command.impl.goCommands.*;
import smytsyk.finalProject.library.controller.command.impl.librarianCommands.GoCommands.*;
import smytsyk.finalProject.library.controller.command.impl.librarianCommands.UpdateUserLibrarianCommand;
import smytsyk.finalProject.library.controller.command.impl.librarianCommands.ordersCommands.AcceptOrderCommand;
import smytsyk.finalProject.library.controller.command.impl.librarianCommands.ordersCommands.CloseOrderCommand;
import smytsyk.finalProject.library.controller.command.impl.librarianCommands.ordersCommands.RejectOrderCommand;
import smytsyk.finalProject.library.controller.command.impl.readerCommands.GoCommands.GoToCatalogPageCommand;
import smytsyk.finalProject.library.controller.command.impl.readerCommands.GoCommands.GoToReaderOrdersPageCommand;
import smytsyk.finalProject.library.controller.command.impl.readerCommands.GoCommands.ReturnToReaderPageCommand;
import smytsyk.finalProject.library.controller.command.impl.readerCommands.SendOrderCommand;
import smytsyk.finalProject.library.controller.command.impl.readerCommands.UpdateUserReaderCommand;

import java.util.HashMap;
import java.util.Map;

/**
 * Container of commands used in this project
 */
public class CommandContainer {
    private static final Map<String, Command> commands = new HashMap<>();

    static {
        commands.put("go_to_main_page", new GoToMainPageCommand());
        commands.put("go_to_login_page", new GoToLoginPageCommand());
        commands.put("go_to_register_page", new GoToRegisterPageCommand());
        commands.put("go_to_error_page", new GoToErrorPageCommand());
        commands.put("login", new LoginCommand());
        commands.put("switch_lang", new SwitchLanguageCommand());
        commands.put("register", new RegisterCommand());
        initAdminCommands();
        initLibrarianCommands();
        initReaderCommands();
        initBannedCommands();
    }

    /**
     * Initializes admin commands
     */
    private static void initAdminCommands() {
        commands.put("add_book", new AddBookCommand());
        commands.put("update_book", new UpdateBookCommand());
        commands.put("delete_book", new DeleteBookCommand());
        commands.put("go_to_admin_users_page", new GoToAdminUsersPageCommand());
        commands.put("go_to_admin_books_page", new GoToAdminBooksPageCommand());
        commands.put("return_to_admin_page", new ReturnToAdminPageCommand());
        commands.put("ban", new BanUserCommand());
        commands.put("unban", new UnbanUserCommand());
        commands.put("appoint", new AddLibrarianCommand());
        commands.put("dismiss", new RemoveLibrarianCommand());
        commands.put("update_reg_admin", new UpdateUserAdminCommand());
    }

    /**
     * Initializes librarian commands
     */
    private static void initLibrarianCommands() {
        commands.put("go_to_librarian_orders_page", new GoToLibrarianOrdersPageCommand());
        commands.put("go_to_librarian_reader_orders_page", new GoToLibrarianReaderOrdersPageCommand());
        commands.put("go_to_librarian_readers_page", new GoToLibrarianReadersPageCommand());
        commands.put("return_to_librarian_page", new ReturnToLibrarianPageCommand());
        commands.put("return_to_librarian_readers_page", new ReturnToLibrarianReadersPageCommand());
        commands.put("accept_order", new AcceptOrderCommand());
        commands.put("reject_order", new RejectOrderCommand());
        commands.put("close_order", new CloseOrderCommand());
        commands.put("update_reg_librarian", new UpdateUserLibrarianCommand());
    }

    /**
     * Initializes reader commands
     */
    private static void initReaderCommands() {
        commands.put("update_reg_reader", new UpdateUserReaderCommand());
        commands.put("send_order", new SendOrderCommand());
        commands.put("return_to_readers_page", new ReturnToReaderPageCommand());
        commands.put("go_to_catalog_page", new GoToCatalogPageCommand());
        commands.put("go_to_reader_orders_page", new GoToReaderOrdersPageCommand());
    }

    /**
     * Initializes banned user commands
     */
    private static void initBannedCommands() {
        commands.put("return_to_banned_page", new ReturnToBannedPageCommand());
        commands.put("update_reg_banned", new UpdateUserBannedCommand());
    }

    /**
     * Method that gets command from container by name
     * Returns Invalid Command if command with such name doesn't exist
     * @param name name of command
     */
    public static Command getCommand(String name) {
        return commands.getOrDefault(name, new InvalidCommand());
    }
}
