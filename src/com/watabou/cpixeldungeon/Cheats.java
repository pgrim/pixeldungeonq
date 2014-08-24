package com.watabou.cpixeldungeon;

public enum Cheats {
	INSTANCE;

	public static boolean Enabled = false;
	public static int StartingStrength = 20;
	public static int StartingHitPoints = 30;

	private static boolean _enableHuntress = true;

	public static boolean EnableHuntress() {
		return _enableHuntress && Enabled;
	}

	public static void SetEnableHuntress(boolean value) {
		_enableHuntress = value;
	}

	private static boolean _allItemsKnown = true;

	public static boolean AllItemsKnown() {
		return _allItemsKnown && Enabled;
	}

	public static void SetAllItemsKnown(boolean value) {
		_allItemsKnown = value;
	}

	private static boolean _showHiddenDoors = true;

	public static boolean ShowHiddenDoors() {
		return _showHiddenDoors && Enabled;
	}

	public static void SetShowHiddenDoors(boolean value) {
		_showHiddenDoors = value;
	}
	private static boolean _showHiddenTraps = true;

	public static boolean ShowHiddenTraps() {
		return _showHiddenTraps && Enabled;
	}
	public static void SetShowHiddenTraps(boolean value) {
		_showHiddenTraps = value;
	}

	
	private static boolean _alwaysPlaceUpgrade = true;

	public static boolean AlwaysPlaceUpgrade() {
		return _alwaysPlaceUpgrade && Enabled;
	}
	public static void SetAlwaysPlaceUpgrade(boolean value) {
		_alwaysPlaceUpgrade = value;
	}
	
	
	private static boolean _ringOfDetectionGivesMindVision = true;

	public static boolean RingOfDetectionGivesMindVision() {
		return _ringOfDetectionGivesMindVision && Enabled;
	}
	public static void SetRingOfDetectionGivesMindVision(boolean value) {
		_ringOfDetectionGivesMindVision = value;
	}
	
	private static boolean _scrollOfIdentifyGivesMindVision = false;

	public static boolean ScrollOfIdentifyGivesMindVision() {
		return _scrollOfIdentifyGivesMindVision && Enabled;
	}
	public static void SetScrollOfIdentifyGivesMindVision(boolean value) {
		_scrollOfIdentifyGivesMindVision = value;
	}

}
