import groovy.swing.SwingBuilder
import java.awt.BorderLayout as BL

public class clicker1 {
    static final int width = 300;
    static final int height = 300;
    static final String TITLE = 'Frame'
    static final String text1 = 'Click the button!'
    static final String text2 = 'Click Me'
    static final String text3 = 'clicked'
    static final String text4 = 'Clicked %s time(s)'

    static int count = 0

    def frame1 = {
        def action1 = {
            count++;
            textlabel.text = String.format(text4, count);
            println text3;
        }

        borderLayout();
        textlabel = label(text: text1, constraints: BL.NORT);
        button(text: text2, actionPerformed: action1, constraints: BL.SOUTH);
        button(text: "Exit", actionPerformed: {System.exit(0)}, constraints: BL.NORTH);
    }

    def app = {
      frame(title: TITLE, size: [width, height], show: true, frame1)
    }
    
    public static void main(String[] args) {
        def swing = new SwingBuilder();
        swing.edt(new clicker1().app);
    }
}