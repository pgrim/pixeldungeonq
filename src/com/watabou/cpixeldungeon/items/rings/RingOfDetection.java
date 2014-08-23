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
package com.watabou.cpixeldungeon.items.rings;

import com.watabou.cpixeldungeon.Dungeon;
import com.watabou.cpixeldungeon.Cheats;
import com.watabou.cpixeldungeon.actors.buffs.Buff;
import com.watabou.cpixeldungeon.actors.buffs.MindVision;
import com.watabou.cpixeldungeon.actors.hero.Hero;
import com.watabou.cpixeldungeon.utils.GLog;

public class RingOfDetection extends Ring {

	{
		name = "Ring of Detection";
	}
	
	@Override
	public boolean doEquip( Hero hero ) {
		if (super.doEquip( hero )) {
			if (Cheats.RingOfDetectionGivesMindVision())
			{
				Buff.affect( hero, MindVision.class, 100 );
				Dungeon.observe();
				
				if (Dungeon.level.mobs.size() > 0) {
					GLog.i( "You can somehow feel the presence of other creatures' minds!" );
				} else {
					GLog.i( "You can somehow tell that you are alone on this level at the moment." );
				}
			}
			else
			{
				Dungeon.hero.search( false );
			}
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	protected RingBuff buff( ) {
		return new Detection();
	}
	
	@Override
	public String desc() {
		if (Cheats.RingOfDetectionGivesMindVision())
		{
			return "With Cheats: After putting this on, your mind will become attuned to the psychic signature " +
					"of distant creatures, enabling you to sense biological presences through walls. " +
					"Also this ring will permit you to see through nearby walls and doors.";
		}
		else
		{
			return isKnown() ?
				"Wearing this ring will allow the wearer to notice hidden secrets - " +
				"traps and secret doors - without taking time to search. Degraded rings of detection " +
				"will dull your senses, making it harder to notice secrets even when actively searching for them." :
				super.desc();
		}
	}
	
	public class Detection extends RingBuff {
	}
}
