---
--- Generated by EmmyLua(https://github.com/EmmyLua)
--- Created by mike.
--- DateTime: 04.08.18 18:14
---

local RPD = require "scripts/lib/commonClasses"

local actor = require "scripts/lib/actor"

local dungeonEntrance = {26, 24}
local shopEntrance    = {15, 19}

local lastWarningTime = 0
local time = 0

return actor.init({
    act = function()
        local hero = RPD.Dungeon.hero
        local belongings = hero.belongings

        time = time + 1

        if belongings.weapon then
            RPD.Dungeon.level:setCompassTarget(table.unpack(dungeonEntrance))
        else
            RPD.Dungeon.level:setCompassTarget(table.unpack(shopEntrance))

            if time - lastWarningTime > 10 then
                local heroPos = RPD.getXy(hero)

                local d2 = (dungeonEntrance[1] - heroPos[1])^2 + (dungeonEntrance[2] - heroPos[2])^2
                if d2 < 9 then
                    hero:interrupt()
                    lastWarningTime = time
                    local wnd = RPD.new(RPD.Objects.Ui.WndQuest,hero,RPD.textById("TownScript_NeedWeapon"))
                    RPD.GameScene:show(wnd)
                end
            end
        end

        return true
    end,
    actionTime = function()
        return 1
    end,
    activate = function()

    end
})