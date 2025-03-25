import { View, Text, Image, Dimensions } from "react-native";
import React from "react";
import CustomButton from "@/components/CustomButton";
import { Redirect, router } from "expo-router";
import { SafeAreaView } from "react-native-safe-area-context";
import { useGlobalContext } from "@/context/GlobalProvider";

const index = () => {

  const { isLogged } = useGlobalContext();
  console.log(isLogged);
  if (isLogged) return <Redirect href="/(tabs)" />;

  return (
    <SafeAreaView className="bg-primaryBlack h-full">
      <View className="flex-1 items-center justify-center">
        <Image
          source={require("../assets/images/DocBridge.jpg")}
          className="h-[100] w-[100] self-center"
          style={{
            minWidth: Dimensions.get("window").width,
          }}
        />
        <CustomButton
          title="Get Started"
          handlePress={() => router.push("/(auth)/sign-in")}
          containerStyles="w-[80%] mt-7"
          textStyles={undefined}
          isLoading={undefined}
        />
      </View>
    </SafeAreaView>
  );
};

export default index;
