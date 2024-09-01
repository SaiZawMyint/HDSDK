package com.itech.products.jni.generator.hdsdk.helper;

import com.itech.products.jni.generator.hdsdk.HDSDKiTech;
import com.sun.jna.Memory;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.WString;

/**
 * LEDScreenManager
 * 
 * 
 * @author SaiZawMyint
 */
public class LEDScreenManager {

    public static final int SCREEN_WIDTH = 64;
    public static final int SCREEN_HEIGHT = 64;
    public static final int COLOR = 1;
    public static final int GRAY = 1;
    public static final int CARD_TYPE = 0;

    private int programID = -1;
    private Pointer pNULL = new Pointer(0);
    private int currentYPosition = 0;  // Track current Y position for text

    public LEDScreenManager() {
        // Create a screen with default dimensions and settings
        createScreen();
    }

    private void createScreen() {
        int result = HDSDKiTech.INSTANCE.Hd_CreateScreen(SCREEN_WIDTH, SCREEN_HEIGHT, COLOR, GRAY, CARD_TYPE, pNULL, 0);
        if (result != 0) {
            throw new RuntimeException("Error creating screen: " + HDSDKiTech.INSTANCE.Hd_GetSDKLastError());
        }

        // Add a program to the screen
        programID = HDSDKiTech.INSTANCE.Hd_AddProgram(pNULL, 0, 0, pNULL, 0);
        if (programID == -1) {
            throw new RuntimeException("Error adding program: " + HDSDKiTech.INSTANCE.Hd_GetSDKLastError());
        }
    }

    public void addText(String text, String fontName, int textColor, int x, int y, int width, int height, int fontSize) {
        int areaID = HDSDKiTech.INSTANCE.Hd_AddArea(programID, x, y, width, height, pNULL, 0, 0, pNULL, 0);
        if (areaID == -1) {
            throw new RuntimeException("Error adding area: " + HDSDKiTech.INSTANCE.Hd_GetSDKLastError());
        }

        WString wText = new WString(text);
        WString wFontName = new WString(fontName);

        Memory textMemory = new Memory((wText.length() + 1) * Native.WCHAR_SIZE);
        Memory fontMemory = new Memory((wFontName.length() + 1) * Native.WCHAR_SIZE);

        textMemory.setWideString(0, wText.toString());
        fontMemory.setWideString(0, wFontName.toString());

        Pointer pText = textMemory;
        Pointer pFontName = fontMemory;

        int areaItemID = HDSDKiTech.INSTANCE.Hd_AddSimpleTextAreaItem(areaID, pText, textColor, 0, 4, pFontName,
                fontSize, 0, width, height, -1, pNULL, 0);
        if (areaItemID == -1) {
            Native.free(Pointer.nativeValue(pText));
            Native.free(Pointer.nativeValue(pFontName));
            throw new RuntimeException("Error adding text item: " + HDSDKiTech.INSTANCE.Hd_GetSDKLastError());
        }

        // Free memory
        Native.free(Pointer.nativeValue(pText));
        Native.free(Pointer.nativeValue(pFontName));

        // Update Y position for the next text
        currentYPosition += height;
    }
    
    public void clearScreen(String ipAddress) {
        // Convert IP address to Pointer
        WString ipPointer = new WString(ipAddress);
        Memory ipMemory = new Memory((ipPointer.length() + 1) * Native.WCHAR_SIZE);
        ipMemory.setWideString(0, ipPointer.toString());

        // Call the clear screen command
        int result = HDSDKiTech.INSTANCE.Cmd_ClearScreen(0, ipMemory, pNULL);
        if (result != 0) {
            throw new RuntimeException("Error clearing screen: " + HDSDKiTech.INSTANCE.Hd_GetSDKLastError());
        }
    }

    public void sendScreen(String ipAddress) {
        int sendType = 0;
        WString sendParamsString = new WString(ipAddress);
        Memory paramsMemory = new Memory((sendParamsString.length() + 1) * Native.WCHAR_SIZE);
        paramsMemory.setWideString(0, sendParamsString.toString());

        int result = HDSDKiTech.INSTANCE.Hd_SendScreen(sendType, paramsMemory, pNULL, pNULL, 0);
        if (result != 0) {
            throw new RuntimeException("Error sending screen: " + HDSDKiTech.INSTANCE.Hd_GetSDKLastError());
        }
    }

	public int getProgramID() {
		return programID;
	}

	public void setProgramID(int programID) {
		this.programID = programID;
	}

	public Pointer getpNULL() {
		return pNULL;
	}

	public void setpNULL(Pointer pNULL) {
		this.pNULL = pNULL;
	}

	public int getCurrentYPosition() {
		return currentYPosition;
	}

	public void setCurrentYPosition(int currentYPosition) {
		this.currentYPosition = currentYPosition;
	}

	public static int getScreenWidth() {
		return SCREEN_WIDTH;
	}

	public static int getScreenHeight() {
		return SCREEN_HEIGHT;
	}

	public static int getColor() {
		return COLOR;
	}

	public static int getGray() {
		return GRAY;
	}

	public static int getCardType() {
		return CARD_TYPE;
	}
    
}
