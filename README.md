# minecraft-locale-api
 A minecraft plugin where it helps create message files and returns the message according to the client's language.

## Usage
```java
public class ExamplePlugin extends JavaPlugin {

    private MessageManager messageManager = new MessageManager(this);
	
    @Override
    public void onEnable() {
        //Here you save a predefined file in your IDE in the resources folder for the plugin folder on the server.
        messageManager.saveMessageResource(Locale.en_us, "lang/messages-en_us.yml");
        
        //Here the API returns the message with path 'test' in 'en_us' language
        System.out.println(messageManager.getMessage(Locale.en_us, "test"));
    }
	
}
```
