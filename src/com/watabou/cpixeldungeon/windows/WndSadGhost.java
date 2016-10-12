/*
 * Pixel Dungeon
 * Copyright (C) 2012-2014  Oleg Dolya
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 */
package com.watabou.cpixeldungeon.windows;

import com.watabou.cpixeldungeon.Dungeon;
import com.watabou.cpixeldungeon.actors.hero.Hero;
import com.watabou.cpixeldungeon.actors.mobs.npcs.Ghost;
import com.watabou.cpixeldungeon.items.Item;
import com.watabou.cpixeldungeon.items.quest.DriedRose;
import com.watabou.cpixeldungeon.scenes.PixelScene;
import com.watabou.cpixeldungeon.sprites.ItemSprite;
import com.watabou.cpixeldungeon.ui.RedButton;
import com.watabou.cpixeldungeon.ui.Window;
import com.watabou.cpixeldungeon.utils.GLog;
import com.watabou.cpixeldungeon.utils.Utils;
import com.watabou.noosa.BitmapTextMultiline;

public class WndSadGhost extends Window {
	
	private static final String TXT_ROSE	= 
		"Yes! Yes!!! This is it! Please give it to me! " +
		"And you can take one of these items, maybe they " +
		"will be useful to you in your journey...";
	private static final String TXT_RAT	= 
		"Yes! The ugly bae is slain and I can finally rest... " +
		"Please take one of these items and fuck off, maybe they " +
		"will be useful to you in your decent to hell you";
	private static final String TXT_WEAPON	= "Ghost's cock";
	private static final String TXT_ARMOR	= "Ghost's balls";
	
	private static final int WIDTH		= 120;
	private static final int BTN_HEIGHT	= 18;
	private static final float GAP		= 2;
	
	public WndSadGhost( final Ghost ghost, final Item item ) {
		
		super();
		
		IconTitle titlebar = new IconTitle();
		titlebar.icon( new ItemSprite( item.image(), null ) );
		titlebar.label( Utils.capitalize( item.name() ) );
		titlebar.setRect( 0, 0, WIDTH, 0 );
		add( titlebar );
		
		BitmapTextMultiline message = PixelScene.createMultiline( item instanceof DriedRose ? TXT_ROSE : TXT_RAT, 6 );
		message.maxWidth = WIDTH;
		message.measure();
		message.y = titlebar.bottom() + GAP;
		add( message );
		
		RedButton btnWeapon = new RedButton( TXT_WEAPON ) {
			@Override
			protected void onClick() {
				selectReward( ghost, item, Ghost.Quest.weapon );
			}
		};
		btnWeapon.setRect( 0, message.y + message.height() + GAP, WIDTH, BTN_HEIGHT );
		add( btnWeapon );
		
		RedButton btnArmor = new RedButton( TXT_ARMOR ) {
			@Override
			protected void onClick() {
				selectReward( ghost, item, Ghost.Quest.armor );
			}
		};
		btnArmor.setRect( 0, btnWeapon.bottom() + GAP, WIDTH, BTN_HEIGHT );
		add( btnArmor );
		
		resize( WIDTH, (int)btnArmor.bottom() );
	}
	
	private void selectReward( Ghost ghost, Item item, Item reward ) {
		
		hide();
		
		item.detach( Dungeon.hero.belongings.backpack );
		
		if (reward.doPickUp( Dungeon.hero )) {
			GLog.i( Hero.TXT_YOU_NOW_HAVE, reward.name() );
		} else {
			Dungeon.level.drop( reward, ghost.pos ).sprite.drop();
		}
		
		ghost.yell( "Hope you die, adventurer!" );
		ghost.die( null );
		
		Ghost.Quest.complete();
	}
}
