package au.com.addstar.truehardcore;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class HardcorePlayers {

	private Map<String, HardcorePlayer> Players;
	public HardcorePlayers() {
		Players = new HashMap<String, HardcorePlayer>();
	}
	
	public static enum PlayerState {
		NOT_IN_GAME,
		IN_GAME,
		ALIVE,
		DEAD;
	}
	
	static class HardcorePlayer {
		private String PlayerName;
		private String World;
		private Location LastPos;
		private Date LastJoin;
		private Date LastQuit;
		private Date GameStart;
		private Date GameEnd;
		private Integer GameTime;
		private Integer Level;
		private float Exp;
		private Integer Score;
		private Integer TopScore;
		private PlayerState State = PlayerState.NOT_IN_GAME;
		private String DeathMsg;
		private Location DeathPos;
		private Integer Deaths;
		private Integer CowKills, PigKills, SheepKills, ChickenKills;
		private Integer CreeperKills, ZombieKills, SkeletonKills, SpiderKills, EnderKills, SlimeKills;
		private Integer OtherKills, PlayerKills;
		private boolean Modified;
		
		public String getPlayerName() {
			return PlayerName;
		}
		public void setPlayerName(String playerName) {
			setModified(true);
			PlayerName = playerName;
		}
		public String getWorld() {
			return World;
		}
		public void setWorld(String world) {
			setModified(true);
			World = world;
		}
		public Location getLastPos() {
			return LastPos;
		}
		public void setLastPos(Location lastPos) {
			setModified(true);
			LastPos = lastPos;
		}
		public Date getLastJoin() {
			return LastJoin;
		}
		public void setLastJoin(Date lastJoin) {
			setModified(true);
			LastJoin = lastJoin;
		}
		public Date getLastQuit() {
			return LastQuit;
		}
		public void setLastQuit(Date lastQuit) {
			setModified(true);
			LastQuit = lastQuit;
		}
		public Date getGameStart() {
			return GameStart;
		}
		public void setGameStart(Date gameStart) {
			setModified(true);
			GameStart = gameStart;
		}
		public Date getGameEnd() {
			TrueHardcore.instance.DebugLog("GET GAMEEND: " + GameEnd);
			return GameEnd;
		}
		public void setGameEnd(Date gameEnd) {
			TrueHardcore.instance.DebugLog("SET GAMEEND: " + gameEnd);
			setModified(true);
			GameEnd = gameEnd;
		}
		public Integer getGameTime() {
			return GameTime;
		}
		public void setGameTime(Integer gameTime) {
			setModified(true);
			GameTime = gameTime;
		}
		public Integer getLevel() {
			return Level;
		}
		public void setLevel(Integer level) {
			setModified(true);
			Level = level;
		}
		public float getExp() {
			return Exp;
		}
		public void setExp(float exp) {
			setModified(true);
			Exp = exp;
		}
		public Integer getScore() {
			return Score;
		}
		public void setScore(Integer score) {
			setModified(true);
			Score = score;
		}
		public Integer getTopScore() {
			return TopScore;
		}
		public void setTopScore(Integer topScore) {
			setModified(true);
			TopScore = topScore;
		}
		public PlayerState getState() {
			return State;
		}
		public void setState(PlayerState state) {
			if ((state == PlayerState.DEAD) && (State != PlayerState.DEAD)) {
				// Player has died
				setGameEnd(new Date());
				setLastQuit(new Date());
			}
			else if ((state == PlayerState.IN_GAME) && (State != PlayerState.IN_GAME)) {
				// Joining a game
				if (State != PlayerState.ALIVE) {
					// Starting a new game
					setGameStart(new Date());
				}
				// Always set the join date when transitioning -> IN_GAME
				setLastJoin(new Date());
			}
			else if ((State == PlayerState.IN_GAME) && (state != PlayerState.IN_GAME)) {
				// Leaving a game (for any reason)
				setLastQuit(new Date());
			}
			State = state;
		}
		public String getDeathMsg() {
			return DeathMsg;
		}
		public void setDeathMsg(String deathMsg) {
			setModified(true);
			DeathMsg = deathMsg;
		}
		public Location getDeathPos() {
			return DeathPos;
		}
		public void setDeathPos(Location deathPos) {
			setModified(true);
			DeathPos = deathPos;
		}
		public Integer getDeaths() {
			return Deaths;
		}
		public void setDeaths(Integer deaths) {
			setModified(true);
			Deaths = deaths;
		}

		public Integer getCowKills() {
			return CowKills;
		}
		public void setCowKills(Integer cowKills) {
			CowKills = cowKills;
		}
		public Integer getPigKills() {
			return PigKills;
		}
		public void setPigKills(Integer pigKills) {
			PigKills = pigKills;
		}
		public Integer getSheepKills() {
			return SheepKills;
		}
		public void setSheepKills(Integer sheepKills) {
			SheepKills = sheepKills;
		}
		public Integer getChickenKills() {
			return ChickenKills;
		}
		public void setChickenKills(Integer chickenKills) {
			ChickenKills = chickenKills;
		}
		public Integer getCreeperKills() {
			return CreeperKills;
		}
		public void setCreeperKills(Integer creeperKills) {
			CreeperKills = creeperKills;
		}
		public Integer getZombieKills() {
			return ZombieKills;
		}
		public void setZombieKills(Integer zombieKills) {
			ZombieKills = zombieKills;
		}
		public Integer getSkeletonKills() {
			return SkeletonKills;
		}
		public void setSkeletonKills(Integer skeletonKills) {
			SkeletonKills = skeletonKills;
		}
		public Integer getSpiderKills() {
			return SpiderKills;
		}
		public void setSpiderKills(Integer spiderKills) {
			SpiderKills = spiderKills;
		}
		public Integer getEnderKills() {
			return EnderKills;
		}
		public void setEnderKills(Integer enderKills) {
			EnderKills = enderKills;
		}
		public Integer getSlimeKills() {
			return SlimeKills;
		}
		public void setSlimeKills(Integer slimeKills) {
			SlimeKills = slimeKills;
		}
		public Integer getPlayerKills() {
			return PlayerKills;
		}
		public void setPlayerKills(Integer playerKills) {
			PlayerKills = playerKills;
		}
		public Integer getOtherKills() {
			return OtherKills;
		}
		public void setOtherKills(Integer otherKills) {
			OtherKills = otherKills;
		}

		
		public void updatePlayer(Player player) {
			setModified(true);
			setExp(player.getExp());
			setLastPos(player.getLocation());
			setScore(player.getTotalExperience());
			setLevel(player.getLevel());
		}
		public void calcGameTime() {
			Date d1 = getLastJoin();
			Date d2 = getLastQuit();

			TrueHardcore.instance.DebugLog("DATES: " + d1 + " / " + d2);
			
			// Only calculate game time if quit is after join
			if (d2.after(d1)) {
				int diff = (int) ((d2.getTime() - d1.getTime()) / 1000);
				TrueHardcore.instance.DebugLog("DIFF: " + diff);
				TrueHardcore.instance.DebugLog("NEW : " + (getGameTime() + diff));
				setGameTime(getGameTime() + diff);
				setModified(true);
			}
		}
		public boolean isModified() {
			return Modified;
		}
		public void setModified(boolean modified) {
			Modified = modified;
		}
	}
	
	public HardcorePlayer NewPlayer(String world, String name) {
		HardcorePlayer hcp = new HardcorePlayer();
		if (hcp != null) {
			hcp.setPlayerName(name.toLowerCase());
			hcp.setWorld(world);
			AddPlayer(world, name.toLowerCase(), hcp);
		}
		return hcp;
	}

	public HardcorePlayer Get(String world, String name) {
		String key = world + "/" + name.toLowerCase();
		if (Players.containsKey(key)) {
			HardcorePlayer hcp = Players.get(key);
			return hcp;
		}
		return null;
	}
	
	public HardcorePlayer Get(World world, Player player) {
		if ((world == null) || (player == null)) { return null; }
		return Get(world.getName(), player.getName());
	}

	public HardcorePlayer Get(Player player) {
		if (player == null) { return null; }
		return Get(player.getLocation().getWorld().getName(), player.getName());
	}
	
	public HardcorePlayer Get(String key) {
		if (Players.containsKey(key)) { return null; }
		return Players.get(key);
	}
	
	public boolean AddPlayer(String world, String name, HardcorePlayer hcp) {
		String key = world + "/" + name.toLowerCase();
		Players.put(key, hcp);
		return true;
	}
	
	public boolean IsHardcorePlayer(Player player) {
		return (Get(player) != null);
	}
	
	public void Clear() {
		Players.clear();
	}
	
	public Map<String, HardcorePlayer> AllRecords() {
		return Players; 
	}
}
