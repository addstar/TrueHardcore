package au.com.addstar.truehardcore;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

class Util {

	static class LogFormatter extends Formatter {
	    //
	    // Create a DateFormat to format the logger timestamp.
	    //
	    private static final DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
	 
	    public String format(LogRecord record) {
	        StringBuilder builder = new StringBuilder(1000);
	        builder.append(df.format(new Date(record.getMillis()))).append(" ");
	        //builder.append("[").append(record.getSourceClassName()).append(".");
	        //builder.append(record.getSourceMethodName()).append("] - ");
	        builder.append("[").append(record.getLevel()).append("]: ");
	        builder.append(formatMessage(record));
	        builder.append("\n");
	        return builder.toString();
	    }
	 
	    public String getHead(Handler h) {
	        return super.getHead(h);
	    }
	 
	    public String getTail(Handler h) {
	        return super.getTail(h);
	    }
	}
	
	public static String padRight(String s, int n) {
	     return String.format("%1$-" + n + "s", s);  
	}
	
	public static String padLeft(String s, int n) {
	    return String.format("%1$" + n + "s", s);  
	}

	public static String Loc2Str(Location loc) {
		if (loc == null) { return null; }
		String result = loc.getWorld().getName() + "," +
						loc.getX() + "," + 
						loc.getY() + "," +
						loc.getZ() + "," +
						loc.getYaw() + "," +
						loc.getPitch();
		
		return result; 
	}
	
	public static Location Str2Loc(String input) {
		if (input == null) { return null; }

		Location loc;
		String[] parts = input.split(",");

		World world = TrueHardcore.instance.getServer().getWorld(parts[0]);
		loc = new Location(world,
				Double.parseDouble(parts[1]),
				Double.parseDouble(parts[2]),
				Double.parseDouble(parts[3]),
				Float.parseFloat(parts[4]),
				Float.parseFloat(parts[5]));
		return loc;
	}
	
	public static String Long2Time(long time) {
		long dS = (time % 60);
		long dM = ((time / 60) % 60);
		long dH = ((time / (60 * 60)) % 24);
		long dD = (time / (24 * 60 * 60));

		String result = "";
		String d = "", h = "", m = "", s = "";
		
		if (dS > 0) s = dS + "s";
		if (dM > 0) m = dM + "m";
		if (dH > 0) h = dH + "h";
		if (dD > 0) d = dD + "d";

		if ((m.length() == 0) && (s.length() > 0) && (h.length() > 0)) m = dM + "m"; 
		if ((h.length() == 0) && (m.length() > 0) && (d.length() > 0)) h = dH + "h"; 

		if ((m.length() > 0) && (s.length() > 0)) m += " ";
		if ((h.length() > 0) && (m.length() > 0)) h += " ";
		if ((d.length() > 0) && (h.length() > 0)) d += " ";
		
		result = d + h + m + s;

		return result;
	}
	
	// TODO: add exception checking
	public static String Date2Mysql(Date date) {
		if (date == null) { return null; }

		SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String mysqldate = sdf.format(date);
		return mysqldate;
	}

	// TODO: add exception checking
	public static Date Mysql2Date(String mysqldate) {
		if ((Objects.equals(mysqldate, "")) || (mysqldate == null) || (Objects.equals(mysqldate, "0000-00-00 00:00:00"))) {
			return null;
		}
		
		SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			return sdf.parse(mysqldate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public static Material GetMaterial(String name) {
		Material mat = Material.matchMaterial(name);
		if (mat != null) {
			return mat;
		}
		return null;
	}
	
	public static boolean GiveItemStack(Player player, ItemStack itemstack) {
		PlayerInventory inventory = player.getInventory();
		inventory.addItem(itemstack);
		//TODO: Check "result" to ensure all items were given
		return true;
	}

	/*
	 * Check if the player has the specified permission
	 */
	private static boolean HasPermission(Player player, String perm) {
		if (player != null) {
			// Real player
			if (player.hasPermission(perm)) {
				return true;
			}
		} else {
			// Console has permissions for everything
			return true;
		}
		return false;
	}
	
	/*
	 * Check required permission and send error response to player if not allowed
	 */
	public static boolean RequirePermission(Player player, String perm) {
		if (!HasPermission(player, perm)) {
			player.sendMessage(ChatColor.RED + "Sorry, you do not have permission for this command.");
			return false;
		}
		return true;
	}

	/*
	 * Check if player is online
	 */
	public static boolean IsPlayerOnline(String player) {
		if (player == null) { return false; }
		if (Objects.equals(player, "")) {
			return false;
		}
		if (TrueHardcore.instance.getServer().getPlayer(player) != null) {
			// Found player.. they must be online!
			return true;
		}
		return false;
	}

	public static boolean Teleport(Player player, Location loc) {
		if ((loc == null) || (player == null)) {
			TrueHardcore.instance.DebugLog("Teleport location or player null!");
			return false;
		} else {
			TrueHardcore.instance.DebugLog("Teleport (" + player.getName() + "): " + loc);
			/*Location newloc = new Location(
					loc.getWorld(),
					(double) loc.getBlockX() + 0.5,
					(double) loc.getBlockY(),
					(double) loc.getBlockZ() + 0.5
			);*/
			return player.teleport(loc);
		}
	}	
}