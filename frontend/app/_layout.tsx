import { useEffect } from "react";
import { Stack } from "expo-router";
import { StatusBar } from "expo-status-bar";
import { useFonts } from "expo-font";
import "../global.css";
import { SafeAreaProvider } from "react-native-safe-area-context";
import GlobalProvider from "../context/GlobalProvider";
declare global {
  interface Window {
    frameworkReady?: () => void;
  }
}

export default function RootLayout() {
  const [fontsLoaded, error] = useFonts({
    "Poppins-Bold": require("../assets/fonts/Poppins-Bold.ttf"),
    "Poppins-Regular": require("../assets/fonts/Poppins-Regular.ttf"),
    "Poppins-SemiBold": require("../assets/fonts/Poppins-SemiBold.ttf"),
  });
  useEffect(() => {     
  }, []);

  return (
    <GlobalProvider>
      <SafeAreaProvider>
        <Stack screenOptions={{ headerShown: false }}>
          <Stack.Screen name="(tabs)" options={{ headerShown: false }} />
          <Stack.Screen name="(auth)" options={{ headerShown: false }} />
          <Stack.Screen name="index" options={{ headerShown: false }} />
        </Stack>
        <StatusBar
          backgroundColor="#121212" // Black theme background
          barStyle="light-content" // White text/icons
        />
      </SafeAreaProvider>
    </GlobalProvider>
  );
}
