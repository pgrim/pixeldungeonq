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
package com.watabou.cpixeldungeon.items.scrolls;

import com.watabou.cpixeldungeon.Badges;
import com.watabou.cpixeldungeon.Cheats;
// import com.watabou.cpixeldungeon.Dungeon;
// import com.watabou.cpixeldungeon.actors.buffs.Buff;
// import com.watabou.cpixeldungeon.actors.buffs.MindVision;
import com.watabou.cpixeldungeon.effects.Identification;
import com.watabou.cpixeldungeon.items.Item;
import com.watabou.cpixeldungeon.utils.GLog;
import com.watabou.cpixeldungeon.windows.WndBag;

public class ScrollOfIdentify extends InventoryScroll {

	{
		name = "Scroll of Identify";
		inventoryTitle = "Select an item to identify";
		mode = WndBag.Mode.UNIDENTIFED;
	}
	
	@Override
	protected void onItemSelected( Item item ) {
/*		if (Cheats.ScrollOfIdentifyGivesMindVisionGivesMindVision())
		{
			Buff.affect(hero, MindVision.class, 100 );
			Dungeon.observe();
			
			if (Dungeon.level.mobs.size() > 0) {
				GLog.i( "You can somehow feel the presence of other creatures' minds!" );
			} else {
				GLog.i( "You can somehow tell that you are alone on this level at the moment." );
			}			
		}
		else
		{*/
			curUser.sprite.parent.add( new Identification( curUser.sprite.center().offset( 0, -16 ) ) );
			
			item.identify();
			GLog.i( "It is " + item );
			
			Badges.validateItemLevelAquired( item );
		// }
	}
	
	@Override
	public String desc() {
		if (Cheats.ScrollOfIdentifyGivesMindVision())
		{
		 return  "With Cheats: After reading this, your mind will become attuned to the psychic signature " +
					"of distant creatures, enabling you to sense biological presences through walls. " +
					"Also this ring will permit you to see through nearby walls and doors.";
		}
		else
		{
			return
				"Permanently reveals all of the secrets of a single item.";
		}
	}
	
	@Override
	public int price() {
		return isKnown() ? 30 * quantity : super.price();
	}
}
