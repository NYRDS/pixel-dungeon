package com.nyrds.pixeldungeon.items.guts.weapon.melee;

import com.watabou.pixeldungeon.items.weapon.melee.MeleeWeapon;

public class Claymore extends MeleeWeapon {
	{
		imageFile = "items/swords.png";
		image = 6;
		animation_class = HEAVY_ATTACK;
	}

	public Claymore() {
		super( 6, 1f, 1f );
	}

}
