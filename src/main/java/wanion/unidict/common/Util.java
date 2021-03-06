package wanion.unidict.common;

/*
 * Created by WanionCane(https://github.com/WanionCane).
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

import cpw.mods.fml.common.registry.GameData;
import net.minecraft.item.ItemStack;
import wanion.unidict.Config;
import wanion.unidict.resource.ResourceHandler;

import java.lang.reflect.Field;
import java.util.Comparator;

import static wanion.unidict.Config.enableSpecificKindSort;
import static wanion.unidict.Config.ownerOfEveryThing;

public final class Util
{
    private Util() {}

    public static final Comparator<ItemStack> itemStackComparatorByModName = (!enableSpecificKindSort) ? new Comparator<ItemStack>()
    {
        @Override
        public int compare(ItemStack itemStack1, ItemStack itemStack2) {
            String stack1ModName = getModName(itemStack1);
            if (Config.keepOneEntry && Config.keepOneEntryModBlackSet.contains(stack1ModName))
                ResourceHandler.addToKeepOneEntryModBlackSet(itemStack1);
            return getIndex(stack1ModName) < getIndex(itemStack2) ? -1 : 0;
        }

        private long getIndex(ItemStack itemStack) {
            return ownerOfEveryThing.get(getModName(itemStack));
        }

        private long getIndex(String modName) {
            return ownerOfEveryThing.get(modName);
        }
    } : null;

    @SuppressWarnings("unchecked")
    public static <T, E extends T> E getField(Class clas, String name, Object instance, Class<T> expectedClass) {
        try {
            final Field field = clas.getDeclaredField(name);
            field.setAccessible(true);
            return (E) expectedClass.cast(field.get(instance));
        } catch (Exception e) { e.printStackTrace(); }
        return null;
    }

    public static <E> void setField(Class clas, String name, Object instance, E newInstance) {
        try {
            final Field field = clas.getDeclaredField(name);
            field.setAccessible(true);
            field.set(instance, newInstance);
        } catch (Exception e) { e.printStackTrace(); }
    }

    public static String getModName(final ItemStack itemStack) {
        String name = GameData.getItemRegistry().getNameForObject(itemStack.getItem());
        return name.substring(0, name.indexOf(58));
    }
}