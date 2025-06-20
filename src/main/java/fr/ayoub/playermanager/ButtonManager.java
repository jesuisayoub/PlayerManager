package fr.ayoub.playermanager;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.ChatPaginator;

import java.util.Arrays;
import java.util.List;

public class ButtonManager {

    public static ItemStack add(String name, String desc, Material material) {
        ItemStack itemStack = new ItemStack(material);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName("§f" + name);
        if (desc != null) {
            String[] paginator = ChatPaginator.wordWrap(desc, 30);
            itemMeta.setLore(Arrays.asList(paginator));
        }
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

}
