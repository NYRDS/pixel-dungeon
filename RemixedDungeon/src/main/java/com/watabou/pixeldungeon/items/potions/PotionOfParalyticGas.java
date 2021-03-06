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
package com.watabou.pixeldungeon.items.potions;

import com.nyrds.pixeldungeon.ml.R;
import com.watabou.noosa.Game;
import com.watabou.noosa.audio.Sample;
import com.watabou.pixeldungeon.Assets;
import com.watabou.pixeldungeon.actors.Char;
import com.watabou.pixeldungeon.actors.blobs.Blob;
import com.watabou.pixeldungeon.actors.blobs.ParalyticGas;
import com.watabou.pixeldungeon.items.weapon.missiles.Arrow;
import com.watabou.pixeldungeon.items.weapon.missiles.ParalysisArrow;
import com.watabou.pixeldungeon.scenes.GameScene;

public class PotionOfParalyticGas extends UpgradablePotion {

	{
		labelIndex = 8;
	}

	@Override
	public void shatter( int cell ) {
		
		setKnown();
		
		splash( cell );
		Sample.INSTANCE.play( Assets.SND_SHATTER );
		
		GameScene.add( Blob.seed( cell, (int) (1000 * qualityFactor()), ParalyticGas.class ) );
	}
	
	@Override
	public String desc() {
		return Game.getVar(R.string.PotionOfParalyticGas_Info);
	}

	@Override
	public int basePrice() {
		return 40;
	}

	@Override
	protected void moistenArrow(Arrow arrow, Char owner) {
		int quantity = reallyMoistArrows(arrow);
		
		ParalysisArrow moistenArrows = new ParalysisArrow(quantity);
		owner.collect(moistenArrows);
	}
}
