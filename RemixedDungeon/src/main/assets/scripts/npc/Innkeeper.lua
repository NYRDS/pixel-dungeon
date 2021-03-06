--
-- User: mike
-- Date: 02.01.2018
-- Time: 00:30
-- This file is part of Remixed Pixel Dungeon.
--

local RPD = require "scripts/lib/commonClasses"

local mob = require"scripts/lib/mob"


return mob.init({
    spawn = function(self, level)
        self:collect(RPD.createItem("FriedFish"))
    end,

    interact = function(self, chr)
        RPD.showBuyWindow(self, chr)
    end
})
