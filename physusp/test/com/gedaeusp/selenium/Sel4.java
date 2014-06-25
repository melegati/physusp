/*
Copyright 2014 António Miranda, Caio Valente, Igor Topcin, Jorge Melegati, Thales Paiva, Victor Santos

This file is part of PhysUSP.

PhysUSP is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

PhysUSP is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with PhysUSP. If not, see <http://www.gnu.org/licenses/>.
*/

package com.gedaeusp.selenium;

import java.util.concurrent.TimeUnit;

import org.junit.*;

import static org.junit.Assert.*;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Sel4 {
  private WebDriver driver;
  private String baseUrl;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
	    driver = new FirefoxDriver();
	    Dimension resolution = new Dimension(1280,1024);
	    driver.manage().window().setSize(resolution);
	    baseUrl = Defines.geDomain();
	    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  //@Test
  public void testE6() throws Exception {
    driver.get(baseUrl + "/physusp/");
    driver.findElement(By.id("parameters.calculateAerobic")).click();
    driver.findElement(By.id("parameters.calculateAnaerobicLactic")).click();
    driver.findElement(By.id("parameters.calculateAnaerobicAlactic")).click();
    driver.findElement(By.id("btnNext")).click();
    driver.findElement(By.cssSelector("textarea.copyPaste")).clear();
    driver.findElement(By.cssSelector("textarea.copyPaste")).sendKeys("536\n474\n262\n375\n347\n504\n403\n302\n342\n338\n371\n389\n354\n351\n370\n330\n276\n389\n330\n255\n352\n346\n344\n380\n353\n331\n355\n321\n291\n349\n307\n322\n322\n295\n331\n396\n362\n319\n373\n415\n392\n432\n420\n381\n405\n386\n359\n338\n296\n425\n380\n370\n309\n289\n335\n360\n253\n472\n486\n532\n527\n462\n507\n449\n461\n516\n444\n425\n400\n388\n374\n383\n369\n368\n459\n382\n371\n390\n390\n362\n369\n356\n252\n359\n356\n276\n222\n350\n359\n261\n322\n371\n427\n632\n615\n450\n567\n504\n515\n232\n239\n182\n303\n225\n80\n228\n190\n248\n266\n219\n322\n271\n414\n375\n496\n208\n420\n296\n320\n530\n337\n332\n362\n346\n520\n440\n343\n300\n253\n360\n339\n320\n272\n259\n349\n392\n504\n498\n374\n389\n320\n316\n302\n242\n434\n598\n459\n372\n339\n424\n322\n365\n391\n330\n377\n324\n307\n346\n317\n325\n300\n273\n258\n252\n366\n607\n496\n433\n364\n287\n384\n331\n300\n303\n309\n372\n485\n338\n273\n372\n395\n328\n330\n408\n418\n412\n373\n358\n518\n363");
    driver.findElement(By.cssSelector("textarea.copyPaste")).clear();
    driver.findElement(By.cssSelector("textarea.copyPaste")).sendKeys("00:00:05\n00:00:09\n00:00:13\n00:00:17\n00:00:20\n00:00:25\n00:00:28\n00:00:31\n00:00:35\n00:00:38\n00:00:41\n00:00:44\n00:00:48\n00:00:51\n00:00:54\n00:00:57\n00:01:00\n00:01:04\n00:01:07\n00:01:10\n00:01:13\n00:01:17\n00:01:20\n00:01:23\n00:01:26\n00:01:29\n00:01:33\n00:01:37\n00:01:41\n00:01:45\n00:01:48\n00:01:52\n00:01:55\n00:01:59\n00:02:02\n00:02:05\n00:02:09\n00:02:12\n00:02:15\n00:02:18\n00:02:21\n00:02:24\n00:02:27\n00:02:30\n00:02:32\n00:02:35\n00:02:39\n00:02:42\n00:02:46\n00:02:49\n00:02:52\n00:02:55\n00:02:58\n00:03:02\n00:03:05\n00:03:08\n00:03:13\n00:03:16\n00:03:19\n00:03:22\n00:03:24\n00:03:27\n00:03:30\n00:03:33\n00:03:36\n00:03:39\n00:03:42\n00:03:45\n00:03:49\n00:03:53\n00:03:57\n00:04:00\n00:04:03\n00:04:06\n00:04:09\n00:04:12\n00:04:15\n00:04:18\n00:04:21\n00:04:24\n00:04:27\n00:04:30\n00:04:33\n00:04:37\n00:04:40\n00:04:43\n00:04:47\n00:04:51\n00:04:54\n00:04:58\n00:05:01\n00:05:04\n00:05:07\n00:05:10\n00:05:13\n00:05:15\n00:05:18\n00:05:21\n00:05:26\n00:05:30\n00:05:33\n00:05:39\n00:05:53\n00:06:03\n00:06:09\n00:06:13\n00:06:17\n00:06:20\n00:06:24\n00:06:28\n00:06:32\n00:06:36\n00:06:39\n00:06:43\n00:06:46\n00:06:49\n00:06:53\n00:06:56\n00:06:59\n00:07:02\n00:07:06\n00:07:09\n00:07:11\n00:07:14\n00:07:17\n00:07:20\n00:07:23\n00:07:26\n00:07:30\n00:07:34\n00:07:38\n00:07:41\n00:07:44\n00:07:48\n00:07:52\n00:07:55\n00:07:58\n00:08:01\n00:08:04\n00:08:07\n00:08:10\n00:08:13\n00:08:17\n00:08:20\n00:08:23\n00:08:26\n00:08:29\n00:08:33\n00:08:36\n00:08:39\n00:08:43\n00:08:47\n00:08:50\n00:08:54\n00:08:57\n00:09:01\n00:09:04\n00:09:08\n00:09:11\n00:09:14\n00:09:18\n00:09:21\n00:09:25\n00:09:28\n00:09:31\n00:09:34\n00:09:37\n00:09:40\n00:09:44\n00:09:47\n00:09:51\n00:09:54\n00:09:58\n00:10:01\n00:10:04\n00:10:07\n00:10:10\n00:10:14\n00:10:17\n00:10:21\n00:10:24\n00:10:27\n00:10:30\n00:10:33\n00:10:36\n00:10:39\n00:10:43\n00:10:46\n00:10:50\n00:10:53");
    driver.findElement(By.cssSelector("textarea.copyPaste")).clear();
    driver.findElement(By.cssSelector("textarea.copyPaste")).sendKeys("00:10:56\n00:10:59\n00:11:05\n00:11:08\n00:11:10\n00:11:13\n00:11:19\n00:11:22\n00:11:25\n00:11:27\n00:11:29\n00:11:31\n00:11:34\n00:11:36\n00:11:39\n00:11:41\n00:11:44\n00:11:47\n00:11:48\n00:11:51\n00:11:56\n00:11:59\n00:12:00\n00:12:02\n00:12:05\n00:12:07\n00:12:08\n00:12:10\n00:12:11\n00:12:12\n00:12:13\n00:12:14\n00:12:17\n00:12:20\n00:12:22\n00:12:25\n00:12:27\n00:12:30\n00:12:32\n00:12:34\n00:12:37\n00:12:39\n00:12:44\n00:12:46\n00:12:49\n00:12:53\n00:12:55\n00:12:58\n00:13:00\n00:13:02\n00:13:04\n00:13:07\n00:13:09\n00:13:12\n00:13:14\n00:13:18\n00:13:21\n00:13:24\n00:13:27\n00:13:29\n00:13:32\n00:13:35\n00:13:37\n00:13:40\n00:13:42\n00:13:44\n00:13:46\n00:13:49\n00:13:51\n00:13:55\n00:13:58\n00:14:00\n00:14:03\n00:14:06\n00:14:09\n00:14:12\n00:14:15\n00:14:17\n00:14:20\n00:14:23\n00:14:25\n00:14:28\n00:14:32\n00:14:35\n00:14:38\n00:14:40\n00:14:43\n00:14:46\n00:14:49\n00:14:51\n00:14:53\n00:15:00\n00:15:04\n00:15:07\n00:15:11\n00:15:15\n00:15:18\n00:15:25\n00:15:28\n00:15:32\n00:15:35\n00:15:39\n00:15:42\n00:15:46\n00:15:49\n00:15:54\n00:15:57\n00:16:00\n00:16:04\n00:16:07\n00:16:10\n00:16:12\n00:16:16\n00:16:20\n00:16:23\n00:16:25\n00:16:28\n00:16:31\n00:16:34\n00:16:36\n00:16:39\n00:16:42\n00:16:45\n00:16:51\n00:16:52\n00:16:55\n00:16:58\n00:17:04\n00:17:07\n00:17:10\n00:17:12\n00:17:14\n00:17:17\n00:17:19\n00:17:22\n00:17:24\n00:17:26\n00:17:29\n00:17:32\n00:17:34\n00:17:37\n00:17:40\n00:17:43\n00:17:45\n00:17:48\n00:17:50\n00:17:52\n00:17:55\n00:17:58\n00:18:00\n00:18:03\n00:18:05\n00:18:08\n00:18:11\n00:18:13\n00:18:16\n00:18:18\n00:18:21\n00:18:24\n00:18:26\n00:18:29\n00:18:31\n00:18:36\n00:18:39\n00:18:41\n00:18:44\n00:18:46\n00:18:49\n00:18:51\n00:18:54\n00:18:57\n00:18:59\n00:19:03\n00:19:05\n00:19:08\n00:19:10\n00:19:12\n00:19:15\n00:19:17\n00:19:20\n00:19:23\n00:19:27\n00:19:29\n00:19:31\n00:19:33\n00:19:35\n00:19:39\n00:19:42\n00:19:44\n00:19:46\n00:19:51\n00:19:55\n00:19:58\n00:20:01\n00:20:05\n00:20:08\n00:20:10\n00:20:14\n00:20:17\n00:20:20\n00:20:23\n00:20:26\n00:20:29\n00:20:32\n00:20:35\n00:20:38\n00:20:41\n00:20:44\n00:20:46\n00:20:49\n00:20:51\n00:20:54\n00:20:57\n00:21:00\n00:21:02\n00:21:05\n00:21:07\n00:21:10\n00:21:12\n00:21:14\n00:21:16\n00:21:18\n00:21:20\n00:21:22\n00:21:24\n00:21:26\n00:21:28\n00:21:31\n00:21:33\n00:21:36\n00:21:38\n00:21:41\n00:21:43\n00:21:46\n00:21:49\n00:21:51\n00:21:54\n00:21:59\n00:22:03\n00:22:06\n00:22:09\n00:22:10\n00:22:13\n00:22:15\n00:22:16\n00:22:18\n00:22:19\n00:22:21\n00:22:23\n00:22:24\n00:22:26\n00:22:30\n00:22:32\n00:22:34\n00:22:37\n00:22:39\n00:22:41\n00:22:43\n00:22:45\n00:22:47\n00:22:49\n00:22:52\n00:22:54\n00:22:57\n00:22:58\n00:23:00\n00:23:02\n00:23:04\n00:23:06\n00:23:09\n00:23:11\n00:23:13\n00:23:15\n00:23:17\n00:23:19\n00:23:22\n00:23:24\n00:23:25\n00:23:27\n00:23:29\n00:23:31\n00:23:33\n00:23:35\n00:23:37\n00:23:39\n00:23:41\n00:23:42\n00:23:44\n00:23:46\n00:23:50\n00:23:52\n00:23:54\n00:23:56\n00:23:58\n00:24:00\n00:24:01\n00:24:03\n00:24:05\n00:24:07\n00:24:09\n00:24:11\n00:24:14\n00:24:15\n00:24:17\n00:24:19\n00:24:21\n00:24:22\n00:24:24\n00:24:26\n00:24:28\n00:24:31\n00:24:32\n00:24:34\n00:24:35\n00:24:37\n00:24:38\n00:24:40\n00:24:41\n00:24:44\n00:24:46\n00:24:47\n00:24:48\n00:24:50\n00:24:52\n00:24:53\n00:24:55\n00:24:56\n00:24:57\n00:24:59\n00:25:00\n00:25:03\n00:25:04\n00:25:05\n00:25:07\n00:25:08\n00:25:10\n00:25:12\n00:25:13\n00:25:15\n00:25:16\n00:25:18\n00:25:19\n00:25:21\n00:25:22\n00:25:24\n00:25:25\n00:25:27\n00:25:29\n00:25:31\n00:25:32\n00:25:34\n00:25:35\n00:25:36\n00:25:38\n00:25:39\n00:25:41\n00:25:42\n00:25:44\n00:25:45\n00:25:47\n00:25:48\n00:25:50\n00:25:51\n00:25:52\n00:25:54\n00:25:55\n00:25:57\n00:25:58\n00:25:59\n00:26:00\n00:26:02\n00:26:03\n00:26:04\n00:26:06\n00:26:07\n00:26:08\n00:26:09\n00:26:11\n00:26:12\n00:26:13\n00:26:15\n00:26:16\n00:26:18\n00:26:19\n00:26:20\n00:26:21\n00:26:23\n00:26:24\n00:26:25\n00:26:27\n00:26:28\n00:26:29\n00:26:30\n00:26:32\n00:26:33\n00:26:34\n00:26:35\n00:26:37\n00:26:38\n00:26:39\n00:26:40\n00:26:42\n00:26:43\n00:26:44\n00:26:45\n00:26:47\n00:26:48\n00:26:49\n00:26:50\n00:26:51\n00:26:52\n00:26:54\n00:26:55\n00:26:56\n00:26:57\n00:26:58\n00:26:59\n00:27:00\n00:27:02\n00:27:03\n00:27:04\n00:27:05\n00:27:06\n00:27:07\n00:27:09\n00:27:10\n00:27:11\n00:27:12\n00:27:13\n00:27:14\n00:27:16\n00:27:17\n00:27:18\n00:27:19\n00:27:20\n00:27:22\n00:27:24\n00:27:25\n00:27:26\n00:27:27\n00:27:28\n00:27:29\n00:27:30\n00:27:31\n00:27:32\n00:27:33\n00:27:35\n00:27:36\n00:27:37\n00:27:38\n00:27:39\n00:27:40\n00:27:42\n00:27:43\n00:27:44\n00:27:45\n00:27:46\n00:27:47\n00:27:48\n00:27:49\n00:27:50\n00:27:51\n00:27:52\n00:27:53\n00:27:54\n00:27:55\n00:27:56\n00:27:57\n00:27:58\n00:27:59\n00:28:01\n00:28:02\n00:28:03\n00:28:04\n00:28:05\n00:28:06\n00:28:07\n00:28:08\n00:28:09\n00:28:10\n00:28:11\n00:28:12\n00:28:13\n00:28:14\n00:28:15\n00:28:16\n00:28:17\n00:28:18\n00:28:19\n00:28:20\n00:28:21\n00:28:22\n00:28:23\n00:28:24\n00:28:25\n00:28:26\n00:28:27\n00:28:28\n00:28:29\n00:28:30\n00:28:31\n00:28:32\n00:28:33\n00:28:34\n00:28:35\n00:28:36\n00:28:37\n00:28:38\n00:28:39\n00:28:40\n00:28:41\n00:28:42\n00:28:43\n00:28:44\n00:28:45\n00:28:46\n00:28:48\n00:28:48\n00:28:49\n00:28:51");
    driver.findElement(By.cssSelector("textarea.copyPaste")).clear();
    driver.findElement(By.cssSelector("textarea.copyPaste")).sendKeys("380\n493\n407\n578\n357\n306\n220\n842\n564\n778\n873\n951\n513\n489\n466\n427\n542\n555\n496\n712\n917\n501\n799\n762\n607\n681\n450\n1653\n822\n901\n705\n325\n506\n591\n629\n661\n542\n1050\n745\n1190\n586\n734\n469\n780\n375\n1075\n787\n1047\n1008\n923\n700\n534\n218\n679\n589\n385\n452\n419\n490\n510\n560\n582\n1061\n310\n545\n1105\n482\n309\n719\n450\n229\n291\n448\n272\n342\n391\n321\n709\n635\n506\n1008\n368\n235\n344\n378\n497\n399\n415\n485\n1327\n487\n518\n201\n154\n157\n134\n165\n177\n629\n163\n389\n207\n305\n172\n251\n311\n525\n158\n567\n726\n875\n361\n480\n417\n318\n381\n386\n487\n376\n587\n478\n884\n487\n621\n100\n489\n221\n195\n305\n352\n332\n576\n250\n288\n263\n244\n192\n238\n348\n424\n409\n417\n532\n508\n480\n522\n412\n494\n385\n464\n838\n440\n273\n414\n408\n440\n412\n646\n311\n381\n402\n403\n418\n353\n447\n477\n446\n431\n478\n542\n687\n673\n252\n554\n1033\n1077\n662\n539\n587\n527\n690\n284\n516\n557\n599\n896\n443\n531\n1088\n102\n779\n379\n588\n505\n625\n660\n801\n696\n949\n804\n981\n1162\n1524\n1530\n1126\n1389\n1574\n1638\n1431\n1262\n1820\n1739\n1369\n1472\n1693\n1353\n1764\n1424\n1760\n1735\n1695\n1736\n1763\n1657\n1560\n1632\n1643\n1726\n1802\n1632\n1654\n1260\n1627\n1466\n1961\n1979\n1719\n1588\n1591\n1833\n1950\n2748\n1478\n2578\n2737\n1651\n2349\n1887\n1667\n1754\n1588\n1344\n1610\n1816\n1747\n1634\n1838\n1907\n1675\n2007\n2115\n1346\n1742\n1902\n2111\n1875\n1946\n1984\n1620\n1467\n2264\n2221\n1902\n1816\n2011\n1656\n2145\n2318\n2237\n2394\n2203\n2185\n2262\n2283\n2172\n2341\n2426\n2583\n1799\n1918\n2880\n2123\n2732\n2432\n2502\n2932\n2368\n2735\n2905\n2155\n1998\n2254\n3143\n3118\n2400\n2557\n3048\n2471\n2701\n1879\n2462\n2989\n3263\n2977\n2880\n2894\n2787\n2156\n2199\n2488\n3388\n3147\n3094\n2741\n2531\n2886\n2806\n2857\n2996\n2588\n2555\n3149\n2928\n3135\n2822\n2100\n2932\n2817\n2744\n3027\n3323\n2654\n2913\n2861\n2679\n2934\n3041\n2395\n3200\n3033\n2681\n3165\n3127\n2749\n2845\n2896\n3041\n3301\n2359\n3123\n3108\n3145\n2962\n3081\n2894\n2998\n3079\n3067\n3114\n2910\n2930\n2881\n3165\n3074\n2927\n3020\n3115\n3055\n3055\n2814\n3025\n3270\n2310\n3592\n3134\n3080\n2656\n3094\n3245\n3211\n3066\n2976\n2949\n3010\n3038\n3171\n3130\n3149\n3212\n3027\n3228\n2581\n3474\n3154\n3096\n2469\n3758\n3553\n3205\n3097\n3147\n3292\n3456\n3107\n3183\n2973\n3210\n3208\n3245\n3352\n3280\n2979\n3123\n2883\n3238\n3215\n3010\n3147\n3054\n3267\n2728\n3341\n3281\n3062\n3035\n2416\n3303\n3656\n3134\n3243\n2889\n3062\n3295\n3164\n3284\n3115\n3206\n3160\n3008\n3024\n3188\n3016\n2307\n3745\n3683\n2772\n3604\n3374\n3295\n3250\n3125\n3104\n3102\n3157\n3313\n3466\n3246\n3285\n3142\n3371\n3235\n3434\n3292\n3021\n3040\n3203\n3308\n3360\n3519\n3270\n3126\n3348\n2137\n3531\n3693\n3393\n3422\n3208\n3180\n3115\n3192\n3454\n3236\n3211\n3099\n3175\n3195\n3405\n2811\n3287\n3249\n3308\n3226\n3554\n3443\n3418\n3129\n3255\n3225\n3256\n3234\n3402\n3337\n3236\n3217\n1413\n1372\n4660\n4960\n4108");
    driver.findElement(By.cssSelector("#oxygenConsumptionDuringExercise > div.handsontableInputHolder > textarea.handsontableInput")).clear();
    driver.findElement(By.cssSelector("#oxygenConsumptionDuringExercise > div.handsontableInputHolder > textarea.handsontableInput")).sendKeys("00:28:49");
    driver.findElement(By.cssSelector("#oxygenConsumptionDuringExercise > div.handsontableInputHolder > textarea.handsontableInput")).clear();
    driver.findElement(By.cssSelector("#oxygenConsumptionDuringExercise > div.handsontableInputHolder > textarea.handsontableInput")).sendKeys("00:28:50");
    driver.findElement(By.id("btnNext")).click();
    driver.findElement(By.id("anaerobicLacticParameters.maxLactateConcentration")).clear();
    driver.findElement(By.id("anaerobicLacticParameters.maxLactateConcentration")).sendKeys("4.2");
    driver.findElement(By.id("anaerobicLacticParameters.restLactateConcentration")).clear();
    driver.findElement(By.id("anaerobicLacticParameters.restLactateConcentration")).sendKeys("0.62");
    driver.findElement(By.id("anaerobicLacticParameters.weight")).clear();
    driver.findElement(By.id("anaerobicLacticParameters.weight")).sendKeys("66.33");
    driver.findElement(By.id("btnNext")).click();
    driver.findElement(By.cssSelector("textarea.copyPaste")).clear();
    driver.findElement(By.cssSelector("textarea.copyPaste")).sendKeys("3238\n2243\n3275\n3081\n3075\n2949\n2938\n3078\n2889\n2926\n2981\n2764\n2866\n2595\n2630\n2726\n2824\n1600\n2640\n2333\n2045\n2082\n1993\n2273\n1994\n1907\n2034\n1964\n1965\n1849\n1683\n1121\n1337\n1169\n2084\n1774\n1604\n1131\n1433\n1286\n1106\n1276\n1043\n1220\n1115\n1186\n1178\n1121\n1114\n1107\n1101\n1146\n1223\n1085\n1108\n1174\n1127\n1131\n1055\n1047\n940\n756\n1190\n897\n1010\n1005\n956\n938\n941\n897\n915\n872\n985\n915\n873\n807\n808\n773\n791\n813\n828\n763\n741\n880\n735\n719\n850\n843\n835\n750\n688\n550\n653\n671\n812\n776\n760\n468\n486\n854\n850\n800\n821\n674\n728\n749\n717\n789\n595\n744\n731\n652\n666\n647\n598\n776\n650\n783\n637\n694\n1281\n978\n719\n682\n740\n639\n745\n633\n679\n512\n623\n568\n621\n836\n567\n533\n508\n784\n598\n627\n461\n855\n715\n472\n555\n604\n695\n641\n897\n864\n476\n482\n493\n712\n1093\n835\n638\n569\n298\n565\n671\n361\n874\n629\n732\n841\n717\n463\n532\n623\n368\n647\n580\n449\n521\n933\n896\n730\n649\n545\n605\n485\n923\n732\n800\n645\n645\n914\n724\n637\n729\n1007\n574\n426\n480\n579\n624\n525\n521\n515\n622\n643\n558\n589\n614\n646\n613\n609\n662\n636\n633\n636\n585\n716\n729\n719\n854\n492\n459\n668\n615\n961\n734\n768\n592\n498\n320\n552\n506\n567\n387\n567\n485\n447\n566\n549\n372\n509\n530\n448\n517\n557\n471\n586\n582\n589\n680\n498\n460\n467\n428\n377\n550\n307\n402\n511\n455\n512\n510\n487\n514\n483\n371\n666\n548\n441\n627\n615\n414\n536\n382\n516\n531");
    driver.findElement(By.cssSelector("textarea.copyPaste")).clear();
    driver.findElement(By.cssSelector("textarea.copyPaste")).sendKeys("00:28:52\n00:28:54\n00:28:55\n00:28:56\n00:28:57\n00:28:58\n00:28:59\n00:29:00\n00:29:02\n00:29:03\n00:29:04\n00:29:05\n00:29:06\n00:29:07\n00:29:08\n00:29:09\n00:29:10\n00:29:12\n00:29:13\n00:29:15\n00:29:16\n00:29:17\n00:29:18\n00:29:19\n00:29:21\n00:29:22\n00:29:23\n00:29:24\n00:29:25\n00:29:26\n00:29:28\n00:29:29\n00:29:31\n00:29:33\n00:29:34\n00:29:35\n00:29:37\n00:29:38\n00:29:39\n00:29:41\n00:29:42\n00:29:43\n00:29:44\n00:29:46\n00:29:47\n00:29:48\n00:29:49\n00:29:51\n00:29:52\n00:29:53\n00:29:54\n00:29:55\n00:29:57\n00:29:58\n00:29:59\n00:30:01\n00:30:02\n00:30:03\n00:30:04\n00:30:06\n00:30:07\n00:30:09\n00:30:10\n00:30:12\n00:30:13\n00:30:14\n00:30:15\n00:30:17\n00:30:18\n00:30:19\n00:30:20\n00:30:22\n00:30:23\n00:30:24\n00:30:25\n00:30:26\n00:30:28\n00:30:29\n00:30:30\n00:30:32\n00:30:33\n00:30:34\n00:30:35\n00:30:37\n00:30:38\n00:30:40\n00:30:41\n00:30:43\n00:30:44\n00:30:45\n00:30:47\n00:30:49\n00:30:50\n00:30:52\n00:30:53\n00:30:55\n00:30:56\n00:30:58\n00:31:00\n00:31:02\n00:31:03\n00:31:05\n00:31:07\n00:31:08\n00:31:10\n00:31:11\n00:31:13\n00:31:14\n00:31:15\n00:31:17\n00:31:18\n00:31:20\n00:31:21\n00:31:22\n00:31:24\n00:31:26\n00:31:27\n00:31:29\n00:31:30\n00:31:32\n00:31:33\n00:31:35\n00:31:36\n00:31:38\n00:31:39\n00:31:41\n00:31:42\n00:31:44\n00:31:46\n00:31:47\n00:31:49\n00:31:51\n00:31:53\n00:31:54\n00:31:56\n00:31:58\n00:31:59\n00:32:01\n00:32:03\n00:32:04\n00:32:06\n00:32:07\n00:32:09\n00:32:11\n00:32:12\n00:32:14\n00:32:16\n00:32:17\n00:32:20\n00:32:21\n00:32:23\n00:32:26\n00:32:28\n00:32:29\n00:32:31\n00:32:32\n00:32:34\n00:32:36\n00:32:37\n00:32:38\n00:32:40\n00:32:42\n00:32:44\n00:32:46\n00:32:48\n00:32:49\n00:32:51\n00:32:53\n00:32:55\n00:32:56\n00:32:57\n00:33:00\n00:33:02\n00:33:03\n00:33:07\n00:33:08\n00:33:10\n00:33:12\n00:33:13\n00:33:14\n00:33:16\n00:33:18\n00:33:19\n00:33:21\n00:33:22\n00:33:24\n00:33:26\n00:33:27\n00:33:29\n00:33:30\n00:33:32\n00:33:34\n00:33:36\n00:33:38\n00:33:39\n00:33:41\n00:33:43\n00:33:45\n00:33:47\n00:33:48\n00:33:50\n00:33:52\n00:33:54\n00:33:56\n00:33:57\n00:33:59\n00:34:01\n00:34:03\n00:34:04\n00:34:06\n00:34:08\n00:34:09\n00:34:11\n00:34:13\n00:34:14\n00:34:16\n00:34:18\n00:34:21\n00:34:23\n00:34:25\n00:34:27\n00:34:28\n00:34:30\n00:34:32\n00:34:34\n00:34:36\n00:34:39\n00:34:41\n00:34:43\n00:34:45\n00:34:48\n00:34:50\n00:34:51\n00:34:53\n00:34:56\n00:34:57\n00:34:59\n00:35:01\n00:35:03\n00:35:04\n00:35:06\n00:35:08\n00:35:10\n00:35:12\n00:35:14\n00:35:16\n00:35:18\n00:35:20\n00:35:22\n00:35:25\n00:35:27\n00:35:29\n00:35:31\n00:35:33\n00:35:34\n00:35:36\n00:35:38\n00:35:40\n00:35:42\n00:35:44\n00:35:46\n00:35:48\n00:35:51\n00:35:53\n00:35:55\n00:35:58\n00:36:00\n00:36:02\n00:36:04\n00:36:06\n00:36:09\n00:36:11\n00:36:13");
    driver.findElement(By.id("btnNext")).click();
    driver.findElement(By.id("btnAdvancedResults")).click();
    driver.findElement(By.cssSelector("rect..highcharts-background")).click();
    driver.findElement(By.id("btnAdvancedResults")).click();
    driver.findElement(By.id("btnAdvancedResults")).click();
    driver.findElement(By.cssSelector("path")).click();
    // ERROR: Caught exception [Error: locator strategy either id or name must be specified explicitly.]
    // ERROR: Caught exception [Error: locator strategy either id or name must be specified explicitly.]
    driver.findElement(By.id("btnPrevious")).click();
    driver.findElement(By.id("btnAdvancedResults")).click();
    driver.findElement(By.id("btnPrevious")).click();
    driver.findElement(By.id("anaerobicLacticParameters.maxLactateConcentration")).clear();
    driver.findElement(By.id("anaerobicLacticParameters.maxLactateConcentration")).sendKeys("8");
    driver.findElement(By.id("btnNext")).click();
    driver.findElement(By.id("btnNext")).click();
    driver.findElement(By.id("btnAdvancedResults")).click();
    driver.findElement(By.id("btnPrevious")).click();
    driver.findElement(By.id("btnAdvancedResults")).click();
    driver.findElement(By.id("btnPrevious")).click();
    driver.findElement(By.id("btnNext")).click();
    driver.findElement(By.id("btnNext")).click();
    driver.findElement(By.id("btnPrevious")).click();
    driver.findElement(By.id("btnNext")).click();
  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

}
