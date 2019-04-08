import java.io.BufferedInputStream;
import java.io.IOException;

class Input {
	public static void main(String[] args) {
		BufferedInputStream bis = null;
		try {
			bis = new BufferedInputStream(System.in);
			int c;
			while ((c = bis.read()) != -1) {
				System.out.println(c);
			}			
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}
