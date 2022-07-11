/*
 * Copyright (c) 2022 Gunner <flintshadowrider@gmail.com>
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package com.catvrat.CatVRatPlugin;

import net.runelite.api.Client;
import net.runelite.api.ItemID;
import net.runelite.api.coords.LocalPoint;
//import net.runelite.client.plugins.catvrat.*;
import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.OverlayLayer;
import net.runelite.client.ui.overlay.OverlayPosition;
import net.runelite.client.ui.overlay.OverlayUtil;
import net.runelite.client.game.ItemManager;

import javax.inject.Inject;
import java.awt.*;

class CatOverlay extends Overlay
{
	private final CatVRatPlugin plugin;
	private final Client client;


	@Inject
	private ItemManager itemManager;

	@Inject
	private CatOverlay(Client client, CatVRatPlugin plugin)
	{
		setPosition(OverlayPosition.DYNAMIC);
		setLayer(OverlayLayer.ABOVE_SCENE);
		this.client = client;
		this.plugin = plugin;
	}

	@Override
	public Dimension render(Graphics2D graphics)
	{
		if (CatVRatPlugin.catState.equalsIgnoreCase("warning")) {
			makeFeedingOverlayHint(graphics, Color.ORANGE);
		}
		else if (CatVRatPlugin.catState.equalsIgnoreCase("dangerous")) {
			makeFeedingOverlayHint(graphics, Color.RED);
		}

		return null;
	}

	public void makeFeedingOverlayHint(Graphics2D graphics, Color color)
	{
		LocalPoint localLocation = client.getFollower().getLocalLocation();

		if (localLocation != null)
		{
			OverlayUtil.renderActorOverlayImage(graphics, client.getFollower(), itemManager.getImage(ItemID.RAW_KARAMBWANJI, 1, false), color, 0);
		}

	}
}
