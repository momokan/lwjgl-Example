package lwjgl_example.textinput;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

public class Window {

	public void start() {
		try {
			//  ウインドウを生成する
			Display.setDisplayMode(new DisplayMode(550, 300));
			Display.create();
		} catch(LWJGLException e) {
			e.printStackTrace();
			return;
		}

		try {
			//  OpenGL の初期設定

			//  テクスチャーを有効化する
			GL11.glEnable(GL11.GL_TEXTURE_2D);

			GL11.glMatrixMode(GL11.GL_PROJECTION);
			GL11.glLoadIdentity();
			GL11.glOrtho(0, 550, 0, 300, 0, 100);
			GL11.glMatrixMode(GL11.GL_MODELVIEW);

			String	text = "";

			while (!Display.isCloseRequested()) {
				GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);

			    // fetch keyboard input.
				
			    while (Keyboard.next()) {
			        char    c = Keyboard.getEventCharacter();

			        if ((Keyboard.getEventKey() != Keyboard.KEY_RETURN) && (Character.isIdentifierIgnorable(c))) {
			            continue;
			        }
			        
			        text = text + String.valueOf(c);
			        if (Keyboard.getEventKey() == Keyboard.KEY_RETURN) {
			            System.out.println("Input> " + text);
			            text = "";
			        }
			    }
				
				Display.update();
				Display.sync(60);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			Display.destroy();
		}
	}

	public static void main(String[] args) {
		new Window().start();
	}
}