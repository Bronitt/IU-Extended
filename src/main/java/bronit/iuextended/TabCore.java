package bronit.iuextended;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class TabCore extends CreativeTabs {

    private final int type;

    public TabCore(int type, String name) {
        super(name);
        this.type = type;
    }

    public ItemStack getTabIconItem() {
        switch (type) {
            case 0:
                return new ItemStack(IUEItem.assembler, 1, 0);
        }
        return new ItemStack(IUEItem.empty);
    }
}
