package edu.basics6;

import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings({"MagicNumber", "EmptyBlock"})
public class Task6 {
    private Task6() {
    }

    private static final Map<Integer, String> KNOW_PORTS =
        new HashMap<>() {{
            put(135, "EPMAP");
            put(137, "Служба имен NetBIOS");
            put(138, "Служба датограм NetBIOS");
            put(139, "Служба сеансов NetBIOS");
            put(445, "Microsoft-DS Active Directory");
            put(1900, "Simple Service Discovery Protocol (SSDP)");
            put(5353, "Многоадресный DNS");
            put(5355, "Link-Local Multicast Name Resolution (LLMNR)");
        }};

    private static final int COUNT_PORTS = 49151;

    private static String isKnowPort(int port) {
        if (KNOW_PORTS.containsKey(port)) {
            return KNOW_PORTS.get(port);
        }
        return "";
    }

    public static String[] portsCheck() {
        ArrayList<Integer> portsUsedTcp = new ArrayList<>();
        ArrayList<Integer> portsUsedUdp = new ArrayList<>();

        for (int i = 0; i <= COUNT_PORTS; ++i) {
            try (ServerSocket server = new ServerSocket(i)) {
            } catch (Exception e) {
                portsUsedTcp.add(i);
                continue;
            }

            try (DatagramSocket server = new DatagramSocket(i)) {
            } catch (Exception e) {
                portsUsedUdp.add(i);
            }
        }
        String[] retTable = new String[portsUsedTcp.size() + portsUsedUdp.size() + 1];
        retTable[0] = "Протокол \t Порт \t Сервис\n";
        int curLine = 1;
        int tcp = 0;
        int udp = 0;

        while (tcp < portsUsedTcp.size() || udp < portsUsedUdp.size()) {
            if (tcp < portsUsedTcp.size() && udp < portsUsedUdp.size()) {
                if (portsUsedTcp.get(tcp) < portsUsedUdp.get(udp)) {
                    retTable[curLine++] =
                        portsUsedTcp.get(tcp++) + "\t TCP \t" + isKnowPort(portsUsedTcp.get(tcp - 1)) + "\n";
                } else {
                    retTable[curLine++] =
                        portsUsedUdp.get(udp++) + "\t UDP \t" + isKnowPort(portsUsedUdp.get(udp - 1)) + "\n";
                }

                continue;
            }

            if (udp < portsUsedUdp.size()) {
                retTable[curLine++] =
                    portsUsedUdp.get(udp++) + "\tUDP\t" + isKnowPort(portsUsedUdp.get(udp - 1)) + "\n";
            } else {
                retTable[curLine++] =
                    portsUsedTcp.get(tcp++) + "\tTCP\t" + isKnowPort(portsUsedTcp.get(tcp - 1)) + "\n";
            }
        }

        return retTable;
    }

}
