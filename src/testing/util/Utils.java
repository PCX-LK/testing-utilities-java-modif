package testing.util;

import arc.*;
import arc.util.*;
import mindustry.core.*;
import mindustry.gen.*;
import testing.content.*;

import static mindustry.Vars.*;

public class Utils{
    public static void spawnIconEffect(String sprite){
        TUFx.iconEffect.at(player.x, player.y, 0, "test-utils-" + sprite);
    }

    public static void runCommand(String command){
        boolean shown = ui.chatfrag.shown();
        if(!shown){
            ui.chatfrag.toggle();
            Time.runTask(netClient.getPing() / 1000f * 2f, () -> {
                Call.sendChatMessage("/js " + command);
                ui.chatfrag.toggle();
            });
        }else{
            Call.sendChatMessage("/js " + command);
        }
    }

    public static void runCommandPlayer(String command){
        runCommand("let p=Groups.player.getByID(" + player.id + ");" + command);
    }
    public static void runCommandPlayerShort(String command){
        runCommand("Groups.player.getByID(" + player.id + ")" + command);
    }

    public static String round(float f){
        if(f >= 1_000_000_000){
            return Strings.autoFixed(f / 1_000_000_000, 1) + UI.billions;
        }else if(f >= 1_000_000){
            return Strings.autoFixed(f / 1_000_000, 1) + UI.millions;
        }else if(f >= 1000){
            return Strings.autoFixed(f / 1000, 1) + UI.thousands;
        }else{
            return (int)f + "";
        }
    }
}
