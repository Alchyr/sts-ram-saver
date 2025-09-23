package optispire.patches;

import com.badlogic.gdx.graphics.GLTexture;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g3d.utils.DefaultTextureBinder;
import com.badlogic.gdx.graphics.g3d.utils.TextureBinder;
import com.badlogic.gdx.graphics.g3d.utils.TextureDescriptor;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;

@SpirePatch(
        clz = DefaultTextureBinder.class,
        method = "bindTexture"
)
public class G3dBindRealTextures {
    private static GLTexture original = null;

    @SpirePrefixPatch
    public static void Wheeee(TextureBinder __instance, TextureDescriptor textureDesc, boolean rebind) {
        original = null;
        if (textureDesc.texture instanceof Texture) {
            original = textureDesc.texture;
            textureDesc.texture = ((Texture) textureDesc.texture).getRealTexture(false);
        }
    }

    @SpirePostfixPatch
    public static void Whoooo(TextureBinder __instance, TextureDescriptor textureDesc, boolean rebind) {
        if (original != null) {
            textureDesc.texture = original;
            original = null;
        }
    }
}
