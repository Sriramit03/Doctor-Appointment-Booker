import { Tabs } from "expo-router";
import { Ionicons } from "@expo/vector-icons";
import { useFonts } from "expo-font";

export default function TabLayout() {
  return (
    <Tabs
      screenOptions={{
        tabBarActiveTintColor: "#2563eb",
        tabBarInactiveTintColor: "#64748b",
        tabBarStyle: {
          backgroundColor: "#121212",
          borderTopWidth: 0,
          height: 60,
          paddingBottom: 8,
          paddingTop: 8,
        },
        headerStyle: {
          backgroundColor: "#121212", // Set header background to dark mode
        },
        headerTintColor: "#ffffff", // Set header text/icons to white
      }}
    >
      <Tabs.Screen
        name="index"
        options={{
          title: "Find Doctor",
          headerShown:false,
          tabBarIcon: ({ size, color }) => (
            <Ionicons name="medical" size={size} color={color} />
          ),
        }}
      />
      <Tabs.Screen
        name="appointments"
        options={{
          title: "My Appointments",
          headerShown:false,
          tabBarIcon: ({ size, color }) => (
            <Ionicons name="calendar" size={size} color={color} />
          ),
        }}
      />
    </Tabs>
  );
}
