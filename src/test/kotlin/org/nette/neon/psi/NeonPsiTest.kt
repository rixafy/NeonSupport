package org.nette.neon.psi

import com.intellij.testFramework.HeavyPlatformTestCase
import org.junit.Test
import org.nette.neon.BasePsiParsingTestCase
import org.nette.neon.parser.NeonParserDefinition
import java.net.URL

class NeonPsiTest : BasePsiParsingTestCase(NeonParserDefinition()) {
    init {
        //HeavyPlatformTestCase.doAutodetectPlatformPrefix()
    }

    @Throws(Exception::class)
    override fun setUp() {
        super.setUp()
        // initialize configuration with test configuration
        //LatteConfiguration.getInstance(getProject(), getXmlFileData());
        //getProject().registerService(LatteSettings.class);
    }

    override fun getTestDataPath(): String? {
        val url: URL = checkNotNull(javaClass.getClassLoader().getResource("data/psi/neonPsi"))
        return url.file
    }

    @Test
    fun testDefault() {
        doTest(true, true)
    }

    @Test
    fun testDefault1() {
        doTest(true, true)
    }

    @Test
    fun testDefault2() {
        doTest(true, true)
    }

    @Test
    fun testNested() {
        doTest(true, true)
    }

    @Test
    fun testNested1() {
        doTest(true, true)
    }

    @Test
    fun testArray1() {
        doTest(true, true)
    }

    @Test
    fun testArray2() {
        doTest(true, true)
    }

    @Test
    fun testArray3() {
        doTest(true, true)
    }

    @Test
    fun testArray4() {
        doTest(true, true)
    }

    @Test
    fun testArray5() {
        doTest(true, true)
    }

    @Test
    fun testArray6() {
        doTest(true, true)
    }

    @Test
    fun testArray7() {
        doTest(true, true)
    }

    @Test
    fun testArray8() {
        doTest(true, true)
    }

    @Test
    fun testArray9() {
        doTest(true, true)
    }

    @Test
    fun testArray10() {
        doTest(true, true)
    }

    @Test
    fun testArrayAfterKey() {
        doTest(true, true) //todo: fix this eventuality
    }

    @Test
    fun testArrayComment() {
        doTest(true, true)
    }

    @Test
    fun testArrayEntity() {
        doTest(true, true)
    }

    @Test
    fun testArrayEntity2() {
        doTest(true, true)
    }

    @Test
    fun testArrayIndentedFile() {
        doTest(true, true)
    }

    @Test
    fun testArrayNoSpaceColon() {
        doTest(true, true)
    }

    @Test
    fun testArrayNull() {
        doTest(true, true)
    }

    @Test
    fun testEmptyLineBeginning() {
        doTest(true, true)
    }

    @Test
    fun testMultiline() {
        doTest(true, true)
    }

    @Test
    fun testItemValueAfterNewLine() {
        doTest(true, true) //todo: fix this eventuality
    }

    @Test
    fun testArrayInline() {
        doTest(true, true)
    }

    @Test
    fun testArrayInline2() {
        doTest(true, true)
    }

    @Test
    fun testArrayInline3() {
        doTest(true, true)
    }

    @Test
    fun testErrorClosingBracket() {
        doTest(true, false)
    }

    @Test
    fun testErrorClosingBracket2() {
        doTest(true, false)
    }

    @Test
    fun testErrorIndent() {
        doTest(true, false)
    }

    @Test
    fun testErrorInlineArray() {
        doTest(true, false)
    }

    @Test
    fun testErrorTabSpaceMixing() {
        doTest(true, false)
    }
}
