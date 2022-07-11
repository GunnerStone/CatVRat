package com.catvrat.CatVRatPlugin;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class CatVRatPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(CatVRatPlugin.class);
		RuneLite.main(args);
	}
}