package com.nyrds.pixeldungeon.mobs.guts;

import com.nyrds.pixeldungeon.ai.Hunting;
import com.nyrds.pixeldungeon.mobs.common.IZapper;
import com.watabou.noosa.audio.Sample;
import com.watabou.pixeldungeon.Assets;
import com.watabou.pixeldungeon.Dungeon;
import com.watabou.pixeldungeon.actors.Char;
import com.watabou.pixeldungeon.actors.CharUtils;
import com.watabou.pixeldungeon.actors.blobs.ToxicGas;
import com.watabou.pixeldungeon.actors.buffs.Amok;
import com.watabou.pixeldungeon.actors.buffs.Buff;
import com.watabou.pixeldungeon.actors.buffs.Burning;
import com.watabou.pixeldungeon.actors.buffs.Paralysis;
import com.watabou.pixeldungeon.actors.buffs.Sleep;
import com.watabou.pixeldungeon.actors.buffs.Stun;
import com.watabou.pixeldungeon.actors.buffs.Terror;
import com.watabou.pixeldungeon.actors.mobs.Mob;
import com.watabou.pixeldungeon.levels.traps.LightningTrap;
import com.watabou.pixeldungeon.mechanics.Ballistica;
import com.watabou.utils.Random;

import org.jetbrains.annotations.NotNull;

/**
 * Created by DeadDie on 12.02.2016
 */
public class YogsBrain extends Mob implements IZapper {

    private static final float TIME_TO_ZAP	= 3f;
    private static final float TIME_TO_SUMMON	= 3f;

    {

        hp(ht(350));
        defenseSkill = 30;

        exp = 25;

        addResistance( LightningTrap.Electricity.class );
        addResistance(ToxicGas.class);

        addImmunity(Paralysis.class);
        addImmunity(Amok.class);
        addImmunity(Sleep.class);
        addImmunity(Terror.class);
        addImmunity(Burning.class);
    }

    @Override
    public int attackProc(@NotNull Char enemy, int damage ) {
        //Paralysis proc
        if (Random.Int(3) == 1){
            Buff.affect(enemy, Stun.class);
        }
        return damage;
    }

    @Override
    public int damageRoll() {
        return Random.NormalIntRange(15, 25);
    }

    @Override
    public int attackSkill( Char target ) {
        return 31;
    }

    @Override
    public int dr() {
        return 12;
    }

    @Override
    public boolean canAttack(@NotNull Char enemy) {
        return Ballistica.cast(getPos(), enemy.getPos(), false, true) == enemy.getPos();
    }

    @Override
    protected int zapProc(@NotNull Char enemy, int damage) {


        CharUtils.lightningProc(enemy, damage);
        return damage;
    }

    @Override
    public boolean getCloser(int target) {
		if (getState() instanceof Hunting) {
			return enemySeen && getFurther( target );
		} else {
			return super.getCloser( target );
		}
	}

    @Override
    public boolean act() {

        if (Random.Int(10) < 6){
            return super.act();
        }

        int nightmarePos = Dungeon.level.getEmptyCellNextTo(getPos());

        spend( TIME_TO_SUMMON );

        if (Dungeon.level.cellValid(nightmarePos)) {
            Nightmare nightmare = new Nightmare();
            nightmare.setPos(nightmarePos);
            Dungeon.level.spawnMob(nightmare, 0,getPos());

            Sample.INSTANCE.play(Assets.SND_CURSED);
        }

        return super.act();
    }

    @Override
    public boolean canBePet() {
        return false;
    }
}
