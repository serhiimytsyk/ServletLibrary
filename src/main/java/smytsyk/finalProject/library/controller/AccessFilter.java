package smytsyk.finalProject.library.controller;

import org.apache.log4j.Logger;
import smytsyk.finalProject.library.entitiy.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Authorization filter
 */
public class AccessFilter implements Filter {
    private final Map<Integer, List<String>> roleToCommands = new HashMap<>();
    private final List<String> commandsWithoutControl = new ArrayList<>();
    private static final Logger log = Logger.getLogger(AccessFilter.class);

    /**
     * Initializes the filter
     */
    @Override
    public void init(FilterConfig filterConfig) {
        log.info("Filter initialization started");
        addCommandsWithoutControl();
        List<String> adminCommands = new ArrayList<>();
        addAdminCommands(adminCommands);
        roleToCommands.put(3, adminCommands);
        List<String> librarianCommands = new ArrayList<>();
        addLibrarianCommands(librarianCommands);
        roleToCommands.put(2, librarianCommands);
        List<String> readerCommands = new ArrayList<>();
        addReaderCommands(readerCommands);
        roleToCommands.put(1, readerCommands);
        List<String> bannedCommands = new ArrayList<>();
        addBannedCommands(bannedCommands);
        roleToCommands.put(0, bannedCommands);
        log.info("Filter initialization finished");
    }

    /**
     * Adds commands without control
     */
    public void addCommandsWithoutControl() {
        commandsWithoutControl.add("go_to_main_page");
        commandsWithoutControl.add("go_to_login_page");
        commandsWithoutControl.add("go_to_register_page");
        commandsWithoutControl.add("go_to_error_page");
        commandsWithoutControl.add("login");
        commandsWithoutControl.add("switch_lang");
        commandsWithoutControl.add("register");
        log.info("Commands out of control: " + commandsWithoutControl);
    }

    /**
     * Adds admin-only commands to list
     * @param commands list of commands
     */
    public void addAdminCommands(List<String> commands) {
        commands.add("add_book");
        commands.add("update_book");
        commands.add("delete_book");
        commands.add("go_to_admin_users_page");
        commands.add("go_to_admin_books_page");
        commands.add("return_to_admin_page");
        commands.add("ban");
        commands.add("unban");
        commands.add("appoint");
        commands.add("dismiss");
        commands.add("update_reg_admin");
        log.info("Admin commands: " + commands);
    }

    /**
     * Adds librarian-only commands to list
     * @param commands list of commands
     */
    public void addLibrarianCommands(List<String> commands) {
        commands.add("go_to_librarian_orders_page");
        commands.add("go_to_librarian_reader_orders_page");
        commands.add("go_to_librarian_readers_page");
        commands.add("return_to_librarian_page");
        commands.add("return_to_librarian_readers_page");
        commands.add("accept_order");
        commands.add("reject_order");
        commands.add("close_order");
        commands.add("update_reg_librarian");
        log.info("Librarian commands: " + commands);
    }

    /**
     * Adds reader-only commands to list
     * @param commands list of commands
     */
    public void addReaderCommands(List<String> commands) {
        commands.add("update_reg_reader");
        commands.add("send_order");
        commands.add("return_to_readers_page");
        commands.add("go_to_catalog_page");
        commands.add("go_to_reader_orders_page");
        log.info("Reader commands: " + commands);
    }

    /**
     * Adds banned-only commands to list
     * @param commands list of commands
     */
    public void addBannedCommands(List<String> commands) {
        commands.add("return_to_banned_page");
        commands.add("update_reg_banned");
        log.info("Banned user commands: " + commands);
    }

    /**
     * Passes only commands that are allowed to this user
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletRequest.setCharacterEncoding("UTF-8");
        if (isAllowedAccess(servletRequest)) {
            log.debug("Access allowed");
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            log.debug("Access denied");
            servletRequest.getRequestDispatcher("/error_page.jsp").forward(servletRequest, servletResponse);
        }
    }

    /**
     * Checks if the command is allowed to user
     */
    private boolean isAllowedAccess(ServletRequest request) {
        HttpServletRequest req = (HttpServletRequest) request;
        if (req.getRequestURI().equals("/")) {
            log.debug("Going to main page");
            return true;
        }

        String command = req.getParameter("command");
        log.debug("Trying to perform command " + command);

        if (command == null || command.isEmpty()) return false;
        if (commandsWithoutControl.contains(command)) return true;
        if (req.getSession() == null) return false;
        User user = (User) req.getSession().getAttribute("user");
        if (user == null || roleToCommands.get(user.getRoleId()) == null) return false;
        return roleToCommands.get(user.getRoleId()).contains(command);
    }

    /**
     * Destroys the filter
     */
    @Override
    public void destroy() {
        log.info("Filter destruction started");
        log.info("Filter destruction finished");
    }
}
