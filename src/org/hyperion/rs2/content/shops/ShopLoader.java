package org.hyperion.rs2.content.shops;

import java.util.logging.Logger;

import org.hyperion.rs2.LivingClasses;
import org.hyperion.rs2.content.shops.Shop.ShopContents;
import org.hyperion.rs2.content.shops.Shop.ShopItem;
import org.hyperion.rs2.model.Item;
import org.hyperion.rs2.model.container.Container;
import org.hyperion.rs2.model.container.Container.Type;
import org.hyperion.rs2.model.player.Player;

/**
 * Loads the contents of a shop.
 * @author Stephen Andrews
 */
public class ShopLoader {

	
	/**
	 * The interface id for a shop.
	 */
	public static final int SHOP_INTERFACE = 300;
		
	/**
	 * Logger instance.
	 */
	private static Logger logger = Logger.getLogger(ShopLoader.class.getName());
	
	/**
	 * Displays the shop to the player.
	 */
	public static void displayShop(Player player, Shop shop) {
		Container shopContents = new Container(Type.ALWAYS_STACK, shop.getContents().getLength());
		for (ShopItem item : shop.getContents().getItems()) {
			shopContents.add(new Item(item.getId(), item.getStock()));
		}
		player.getActionSender().sendUpdateItems(SHOP_INTERFACE, 75, 93, shopContents.toArray());
		player.getActionSender().sendUpdateItems(301, 0, 93, player.getInventory().toArray());
		player.getActionSender().sendInterface(300);
		player.getInterfaceState().interfaceOpened(300);
		player.getActionSender().sendInventoryInterface(301);
		player.getActionSender().sendString(300, 76, shop.getName());
	}
	
	/**
	 * Gets the Shop object for the npc that owns it
	 * @param npcId
	 * @return Shop or <code>null</code>.
	 */
	public static Shop getShopForNpc(int npcId) {
		try {
			for(Shop shop : LivingClasses.definitionLoader.getShops()) {
				if(shop.getId() == npcId) {
					return shop;
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}