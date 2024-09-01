package com.itech.products.jni.generator.hdsdk;


import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Pointer;


/**
 * HDSDKiTech <br>
 * 
 * The interface for LED native library
 * 
 * @author SaiZawMyint
 */
public interface HDSDKiTech extends Library{
	HDSDKiTech INSTANCE = Native.load("HDSDK", HDSDKiTech.class);
	
	// Get last error
    int Hd_GetSDKLastError();

    // Create screen
    int Hd_CreateScreen(int nWidth, int nHeight, int nColor, int nGray, int nCardType, Pointer pExParamsBuf, int nBufSize);

    // Add program
    int Hd_AddProgram(Pointer pBoderImgPath, int nBorderEffect, int nBorderSpeed, Pointer pExParamsBuf, int nBufSize);

    // Add area
    int Hd_AddArea(int nProgramID, int nX, int nY, int nWidth, int nHeight, Pointer pBoderImgPath, int nBorderEffect, int nBorderSpeed, Pointer pExParamsBuf, int nBufSize);

    // Add image area item
    int Hd_AddImageAreaItem(int nAreaID, Pointer pPaths, int nShowEffect, int nShowSpeed, int nClearType, int nStayTime, Pointer pExParamsBuf, int nBufSize);

    // Add text area item
    int Hd_AddSimpleTextAreaItem(int nAreaID, Pointer pText, int nTextColor, int nBackGroupColor, int nStyle, Pointer pFontName, int nFontHeight, int nShowEffect, int nShowSpeed, int nClearType, int nStayTime, Pointer pExParamsBuf, int nBufSize);

    // Add time area item
    int Hd_AddTimeAreaItem(int nAreaID, int nShowMode, int bShowDate, int nDateStyle, int bShowWeek, int nWeekStyle, int bShowTime, int nTimeStyle, int nTextColor, Pointer pFontName, int nFontHeight, int nDiffHour, int nDiffMin, Pointer pExParamsBuf, int nBufSize);

    // Create realtime area item
    int Hd_CreateRealtimeArea(int nX, int nY, int nWidth, int nHeight, Pointer pImgPath, int nUseRealTime, int bOnlyShowRealtimeArea, int bSave, int nLivetime, Pointer pExParamsBuf, int nBufSize);

    // Send command
    int Hd_SendCommand(int nSendType, Pointer pStrParams, int nCommandType, Pointer pConText, int nTextLen, Pointer pDeviceGUID, Pointer pOutConText, int[] pOutConTextLen, Pointer pExParamsBuf, int nBufSize);

    // Send screen
    int Hd_SendScreen(int nSendType, Pointer pStrParams, Pointer pDeviceGUID, Pointer pExParamsBuf, int nBufSize);

    // Send realtime area
    int Hd_SendRealTimeArea(int nSendType, Pointer pStrParams, Pointer pDeviceGUID, Pointer pExParamsBuf, int nBufSize);

    // Get Color value
    int Hd_GetColor(int r, int g, int b);

    // Save screen to disk
    int Hd_SaveScreen(Pointer pDirPath);

    // command 
    // Get baudrate
    int Cmd_GetBaudRate(int nPort, int[] pBaudRate, Pointer pDeviceGUID);

    // Set baudrate
    int Cmd_SetBaudRate(int nPort, int nSrcBaudRate, int nDestBaudRate, Pointer pDeviceGUID);

    // Is card online
    int Cmd_IsCardOnline(int nSendType, Pointer pStrParams, Pointer pDeviceGUID);

    // Clear screen
    int Cmd_ClearScreen(int nSendType, Pointer pStrParams, Pointer pDeviceGUID);

    // Restart card
    int Cmd_RestartCard(int nSendType, Pointer pStrParams, Pointer pDeviceGUID);

    // Test screen
    int Cmd_TestScreen(int nSendType, Pointer pStrParams, int nStyle, Pointer pDeviceGUID);

    // adjust time
    int Cmd_AdjustTime(int nSendType, Pointer pStrParams, Pointer pDeviceGUID);

    // set luminance
    int Cmd_SetLuminance(int nSendType, Pointer pStrParams, int nLuminance, Pointer pDeviceGUID);

    // screen ctrl
    int Cmd_ScreenCtrl(int nSendType, Pointer pStrParams, int nCtrlCode, Pointer pDeviceGUID);

    // timeswitch
    int Cmd_TimeSwitch(int nSendType, Pointer pStrParams, int nUse, int nBootuUpTime, int nShutDownTime, Pointer pDeviceGUID);

    // switch program
    int Cmd_SwitchProgram(int nSendType, Pointer pStrParams, int nProgramIndex, Pointer pDeviceGUID);

    // Set ip
    int Cmd_SetIP(Pointer pSrcIP, Pointer pDestIP, Pointer pDestMask, Pointer pDestGateway, Pointer pDeviceGUID);

    // send realtime screen
    int Hd_Rt_SendScreen(int nSendType, Pointer pStrParams, Pointer pDeviceGUID, Pointer pExParamsBuf, int nBufSize);

    // add realtimearea
    int Hd_Rt_AddRealAreaToScreen(int nX, int nY, int nWidth, int nHeight, int nMaxPageCount);

    // send realtime text
    int Hd_Rt_SendRealTimeText(int nSendType, Pointer pStrParams, int nRealTimeAreaIndex, int nMaxPage, int nColor, int nGray, int nX, int nY, int nWidth, int nHeight,
        Pointer pText, int nTextColor, int nBackGroupColor, int nStyle, Pointer pFontName, int nFontHeight, int nShowEffect, int nShowSpeed,
        int nStayTime, int nLiveTime, int bSaveToFlash, Pointer pDeviceGUID);

    // send realtime image
    int Hd_Rt_SendRealTimeImage(int nSendType, Pointer pStrParams, int nRealTimeAreaIndex, int nMaxPage, int nColor, int nGray, int nX, int nY, int nWidth, int nHeight,
        Pointer pPaths, int nShowEffect, int nShowSpeed, int nStayTime, int nLiveTime, int bSaveToFlash, Pointer pDeviceGUID);

    // set screen params
    int Hd_SetScreenParam(int bUseTimerSwitch, int nBootuUpTime, int nShutDownTime, int nBrightnessMode, int nCustomBrightValue);

    // set program params
    int Hd_SetProgramParam(int nProgramID, int nPlayMode, int nPlayLength, Pointer pBorderPath,
        int nBordernSpeed, int nBordernEffect, int nWeekPlayFlag, int nSpecifedTimeEnabled,
        int nTimeStart, int nTimeEnd, int nSpecifedDateEnabled, int nDateStart, int nDateEnd);

    // add Chinese calendar area item
    int Hd_AddChineseCalendarAreaItem(int nAreaID, int nLanguage, int nShowType, int bShowHeavenly, int nHeavenlyColor, int bShowCalendar,
        int nCalendarColor, int bShowSolarTerms, int nSolarTermsColor, int bShowFestival, Pointer pFontName, int nFontHeight, Pointer pExParamsBuf, int nBufSize);

    // add temperature and humidity area item
    int Hd_AddTempAndHumiAreaItem(int nAreaID, int nSensorType, int bUseTemperature, Pointer pTemperatureText, int nTemperatureTextColor, int nTemperatureX, int nTemperatureY,
        int nTemperatureUnit, int nTemperatureUnitColor, int nTemperatureOffset, int nTemperatureValueColor, int bUseHumidity, Pointer pHumidityText, int nHumidityTextColor, int nHumidityX, int nHumidityY,
        int nHumidityUnit, int nHumidityUnitColor, int nHumidityOffset, int nHumidityValueColor,
        Pointer pFontName, int nFontHeight, Pointer pExParamsBuf, int nBufSize);

    // add count area item
    int Hd_AddCountAreaItem(int nAreaID, int nCountMode, int nShowMode, int nAlignment, int nYear, int nMonth, int nDay, int nHour, int nMinute, int nSecond, int nValueColor, int nUnitColor,
        int bShowDay, int bShowHour, int bShowMinute, int bShowSecond, int bShowMS, int bAddUp, int bCycletiming, int nX, int nY, Pointer pFontName, int nFontHeight, Pointer pExParamsBuf, int nBufSize);

    // add digit area item
    int Hd_AddDigitAreaItem(int nAreaID, int nInitialState, int nAlignment, int nMin, int nMinDecimalValue, int nMax, int nMaxDecimalValue, int nDecimalDigit, int nColor,
        int nTransitionTreshold, int nTransitionTresholdDecimalValue, int bUseMinImage, Pointer pMinImagePath, int bUseMaxImage, Pointer pMaxImagePath, int bSaveDigit, int nX, int nY, Pointer pFontName, int nFontHeight, Pointer pExParamsBuf, int nBufSize);

    // clear realtime area
    int Cmd_ClearRealtimeArea(int nSendType, Pointer pStrParams, Pointer pDeviceGUID);

    // set digit state
    int Cmd_SetDigitState(int nSendType, Pointer pStrParams, int nAreaIndex, int nCmdType, int nValue, int nDecimaValue, Pointer pDeviceGUID);

    // set count state
    int Cmd_SetCountState(int nSendType, Pointer pStrParams, int nAreaIndex, int nCmdType, Pointer pDeviceGUID);

}
