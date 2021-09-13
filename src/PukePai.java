import java.util.*;

public class PukePai {
    List<Puke> listPuke = new ArrayList<>();
    List<String> listType = new ArrayList<>();
    List<Puke> listNewSortPuke = new ArrayList<>();
    List<Integer> listNo = new ArrayList<>();//扑克牌编号
    List<Player> listPlayer = new ArrayList<>();

    public static void main(String[] args) {
        PukePai pukePai = new PukePai();
        pukePai.addType();
        System.out.println("--------------创建扑克牌---------------------------");
        pukePai.createPukePai();
        System.out.println("--------------扑克牌创建成功!---------------------------");
        pukePai.getPukeList();
        System.out.println("--------------开始洗牌.....---------------------------");
        pukePai.getNewSortPukeList();
        System.out.println("--------------洗牌结束!---------------------------");
//        pukePai.getNewPukeList();
        System.out.println("--------------创建玩家....---------------------------");
        pukePai.createPlayers(2);
        pukePai.welcomePlayer();
        System.out.println("--------------开始发牌---------------------------");
        pukePai.sendPuke();
        System.out.println("--------------发牌结束!---------------------------");
        System.out.println("--------------开始游戏---------------------------");
        pukePai.playGame();
        System.out.println("--------------玩家各自的手牌为:---------------------------");
        pukePai.getResult();
    }

    /**
     * 获取底牌
     */
    private void getResult() {
        for (Player player : listPlayer) {
            System.out.println(player.getName() + ":" + player.getListPuke());
        }
    }

    /**
     * 开始游戏
     */

    private void playGame() {
        Map<Player, Puke> mapMax = new HashMap<>();
        for (Player player : listPlayer) {
            String name = player.getName();
            List<Puke> listPuke = player.getListPuke();
            Puke maxPuke = getMaxPuke(listPuke);
            System.out.println("玩家: " + name + "最大的手牌为: " + maxPuke.getType() + maxPuke.getName());
            mapMax.put(player, maxPuke);
        }
        Puke maxPuke = null;
        Player winer = null;
        for (Player player : mapMax.keySet()) {
            Puke puke = mapMax.get(player);
            if(maxPuke == null || maxPuke.compareTo(puke) < 0) {
                maxPuke = puke;
                winer = player;
            }
        }
        System.out.println("--------------玩家: " + winer.getName() + "获胜---------------");
    }

    /**
     * 获取最大手牌
     */

    private Puke getMaxPuke(List<Puke> listPuke) {
        Collections.sort(listPuke);
        return listPuke.get(listPuke.size() - 1);
    }

    /**
     * 发牌
     */
    private void sendPuke() {
        int index = 0;
        for (int i=0;i<2;i++) {//一人发两张牌
            for (Player player : listPlayer) {
                List<Puke> listPuke = player.getListPuke();
                if(listPuke == null) {
                    listPuke = new ArrayList<>();
                }
                listPuke.add(listNewSortPuke.get(index));
                player.setListPuke(listPuke);
                System.out.println("---玩家" + player.getName() + "-拿牌");
                index++;
            }
        }
    }

    private void welcomePlayer() {
        for (Player player : listPlayer) {
            System.out.println("---欢迎玩家:" + player.getName());
        }
    }

    /**
     * 创建玩家
     */

    private void createPlayers(int n) {
        Scanner scanner = new Scanner(System.in);
        for (int i=1;i<=n;i++) {
            createPlayer(i, scanner);
        }
    }

    private void createPlayer(int n, Scanner scanner) {
        System.out.println("请输入第" + n + "位玩家的ID和姓名");
        while (true) {
            System.out.println("输入ID:");
            String playerId = scanner.next();
            if(!isValidId(playerId)) {
                System.out.println("请输入整数类型的ID!");
                continue;
            }
            System.out.println("输入姓名:");
            String playerName = scanner.next();
            listPlayer.add(new Player(playerId, playerName));
            break;
        }
    }

    /**
     * 洗牌
     */
    private void getNewSortPukeList() {
        Random random = new Random();
        while (listNewSortPuke.size() < 52) {
            int i = random.nextInt(listNo.size());
            Puke puke = listPuke.get(listNo.get(i));
            if(!listNewSortPuke.contains(puke)) {
                listNewSortPuke.add(puke);
                listNo.remove(i);
            }
        }
    }

    /**
     * 判断输入ID是否是Integer类型
     */

    private boolean isValidId(String id) {
        int length = id.length();
        for (int i = 0; i < length; i++) {
            char c = id.charAt(i);
            if(c < '0' || c > '9') {
                return false;
            }
        }
        return true;
    }

    /**
     * 输出新扑克牌列表
     */
    private void getNewPukeList() {
        System.out.println("为: " + listNewSortPuke);
    }

    /**
     * 输出扑克牌列表
     */
    private void getPukeList() {
        System.out.println("为: " + listPuke);
    }

    /**
     * 增加扑克牌类型
     */

    private void addType() {
        listType.add("方片");
        listType.add("梅花");
        listType.add("红桃");
        listType.add("黑桃");
    }

    /**
     * 创建扑克牌
     */
    private void createPukePai() {
        int no = 0;
        for (int i=0;i<listType.size();i++) {
            String type = listType.get(i);
            for (int j=2;j<15;j++) {
                String name = "";
                if(j >= 11) {
                    name = getExtraNum(j);
                } else {
                    name = "" + j;
                }
                Puke puke = new Puke(type, j, name);
                puke.setTypeIndex(i);
                listPuke.add(puke);
                listNo.add(no);
                no++;
            }
        }
    }

    /**
     * 获取特别的数值
     * 11 -- J
     * 12 --- Q
     * 13 --- K
     * 14 --- A
     */
    private String getExtraNum(int num) {
        switch (num) {
            case 11:
                return "J";
            case 12:
                return "Q";
            case 13:
                return "K";
            case 14:
                return "A";
        }
        return "";
    }
}
