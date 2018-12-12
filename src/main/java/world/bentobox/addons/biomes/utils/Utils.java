package world.bentobox.addons.biomes.utils;


import org.apache.commons.lang.WordUtils;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Biome;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import world.bentobox.addons.biomes.BiomesAddon;
import world.bentobox.addons.biomes.objects.BiomesObject;


/**
 * Utils class that contains useful methods.
 */
public class Utils
{
	/**
	 * Thus method parses input string to ItemStack.
	 * @param inputString Splitted string.
	 * @return ItemStack that represents input string.
	 */
	private static ItemStack parse2ArrayString(String[] inputString)
	{
		int reqAmount;

		try
		{
			reqAmount = Integer.parseInt(inputString[1]);
		}
		catch (Exception e)
		{
			return null;
		}

		Material reqItem = Material.getMaterial(inputString[0].toUpperCase() + "_ITEM");

		if (reqItem == null)
		{
			reqItem = Material.getMaterial(inputString[0].toUpperCase());
		}

		if (reqItem == null)
		{
			return null;
		}

		return new ItemStack(reqItem, reqAmount);
	}


	/**
	 * Create ItemStack from 3 string parts.
	 * @param inputString Splitted string.
	 * @return ItemStack that is created from input string.
	 */
	private static ItemStack parse3ArrayString(String[] inputString)
	{
		String[] twoArrayString = {inputString[0], inputString[2]};

		return Utils.parse2ArrayString(twoArrayString);
	}

	/**
	 * This method parse given string to ItemStack element.
	 * @return the parsed ItemStack element.
	 */
	public static ItemStack parseItem(BiomesAddon addon, String inputString)
	{
		String[] part = inputString.split(":");

		ItemStack itemStack;

		if (part.length == 2)
		{
			itemStack = Utils.parse2ArrayString(part);
		}
		else if (part.length == 3)
		{
			itemStack = Utils.parse3ArrayString(part);
		}
		else
		{
			itemStack = null;
		}

		if (itemStack == null)
		{
			addon.getLogger().severe(() -> "Problem with " + inputString + " in config.yml!");
		}

		return itemStack;
	}


	/**
	 * This method splits input string in multiple string lists.
	 * @param string String that must be splitted.
	 * @return List of splited strings.
	 */
	public static List<String> splitString(String string)
	{
		string = ChatColor.translateAlternateColorCodes('&', string);

		List<String> result = new ArrayList<>();
		Arrays.asList(string.split("\\|")).forEach(
			line -> result.addAll(Arrays.asList(WordUtils.wrap(line, 25).split("\\n"))));

		return result;
	}


	/**
	 * This method parses BiomesObject to necessary Biome.
	 * @param biomesObject BiomesObject.
	 * @return Biome that is represented by BiomesObject.
	 */
	public static Biome parseBiome(BiomesObject biomesObject)
	{
		int id = biomesObject.getBiomesID();

		if (id < 0 || Biome.values().length < id)
		{
			return null;
		}

		return Biome.values()[id];
	}
}
