package wanion.unidict.integration;

/*
 * Created by WanionCane(https://github.com/WanionCane).
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

import com.shinoow.abyssalcraft.api.recipe.TransmutatorRecipes;
import net.minecraft.item.ItemStack;
import wanion.unidict.UniDict;

import java.util.Map;

final class AbyssalCraftIntegration extends AbstractIntegrationThread
{
    AbyssalCraftIntegration()
    {
        super("AbyssalCraft");
    }
    @Override
    public String call()
    {
        try {
            fixTransmutationRecipes();
        } catch (Exception e) { UniDict.getLogger().error(threadName + e); }
        return threadName + "TransMules Fixes!";
    }
    private void fixTransmutationRecipes()
    {
        for (final Map.Entry<ItemStack, ItemStack> entry : TransmutatorRecipes.instance().getTransmutationList().entrySet())
            entry.setValue(resourceHandler.getMainItemStack(entry.getValue()));
    }
}
