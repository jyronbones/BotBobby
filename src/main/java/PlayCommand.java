//import java.awt.Color;
//import java.net.MalformedURLException;
//import java.net.URL;
//import java.util.List;
//
//import javax.annotation.Nullable;
//
//import com.google.api.services.youtube.YouTube;
//import com.google.api.services.youtube.model.SearchResult;
//
//import net.dv8tion.jda.api.EmbedBuilder;
//import net.dv8tion.jda.api.entities.Member;
//import net.dv8tion.jda.api.entities.Message;
//import net.dv8tion.jda.api.entities.TextChannel;
//
//public class PlayCommand implements ServerCommand {
//    private final YouTube youTube;
//
//    public PlayCommand() {
//        YouTube temp = null;
//
//        try {
//            temp = new YouTube.Builder(
//                    GoogleNetHttpTransport.newTrustedTransport(),
//                    JacksonFactory.getDefaultInstance(),
//                    null
//            )
//                    .setApplicationName("JDA Discord Bot")
//                    .build();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        youTube = temp;
//    }
//
//    @Override
//    public void performCommand(List<String> args, Member m, TextChannel channel, Message message) throws RiotApiException {
//        String input = String.join(" ", args.subList(1, args.size() - 1));
//
//        if (!isUrl(input)) {
//            String ytSearched = searchYoutube(channel, input);
//
//            if (ytSearched == null) {
//                channel.sendMessage("play!").queue();
//
//                return;
//            }
//
//
//            input = ytSearched;
//        }
//
//        PlayerManager manager = PlayerManager.getInstance();
//
//        manager.loadAndPlay(channel, input);
//        manager.getGuildMusicManager(channel.getGuild()).player.setVolume(100);
//    }
//
//    private boolean isUrl(String input) {
//        try {
//            new URL(input);
//
//            return true;
//        } catch (MalformedURLException ignored) {
//            return false;
//        }
//    }
//
//    @Nullable
//    private String searchYoutube(TextChannel channel, String input) {
//        String youtubeKey = TOKEN;
//
//        try {
//            List<SearchResult> results = youTube.search()
//                    .list("id,snippet")
//                    .setQ(input)
//                    .setMaxResults(10L)
//                    .setType("video")
//                    .setFields("items(id/kind,id/videoId,snippet/title,snippet/thumbnails/default/url)")
//                    .setKey(youtubeKey)
//                    .execute()
//                    .getItems();
//
//            if (!results.isEmpty()) {
//                String videoId = results.get(0).getId().getVideoId();
//
//
//                EmbedBuilder builder = new EmbedBuilder();
//                builder.setTitle("Suchergebnisse");
//                builder.setColor(Color.RED);
//                builder.setDescription( "1. " + results.get(0).getSnippet().getTitle() + "\n" +
//                                        "2. " + results.get(1).getSnippet().getTitle() + "\n" +
//                                        "3. " + results.get(2).getSnippet().getTitle() + "\n" +
//                                        "4. " + results.get(3).getSnippet().getTitle() + "\n" +
//                                        "5. " + results.get(4).getSnippet().getTitle() + "\n" +
//                                        "6. " + results.get(5).getSnippet().getTitle() + "\n" +
//                                        "7. " + results.get(6).getSnippet().getTitle() + "\n" +
//                                        "8. " + results.get(7).getSnippet().getTitle() + "\n" +
//                                        "9. " + results.get(8).getSnippet().getTitle() + "\n" +
//                                        "10. " + results.get(9).getSnippet().getTitle());
//
//                channel.sendMessage(builder.build()).queue();
//                
//
//
//                return "https://www.youtube.com/watch?v=" + videoId;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return null;
//    }
//}
