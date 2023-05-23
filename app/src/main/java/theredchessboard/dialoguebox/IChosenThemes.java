package theredchessboard.dialoguebox;

public interface IChosenThemes {
    /**
     * The callback function from
     * the theme chooser
     * @param FpTheme The name of 
     * the theme for the first player
     * @param SpTheme The name of 
     * the theme for the second player
     */
    void startBoard(String FpTheme, String SpTheme);
}
