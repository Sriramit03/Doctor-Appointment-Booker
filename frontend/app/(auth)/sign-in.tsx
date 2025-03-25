import { useState } from "react";
import { Link, router } from "expo-router";
import { SafeAreaView } from "react-native-safe-area-context";
import { View, Text, ScrollView, Dimensions, Alert, Image } from "react-native";
import FormField from "@/components/FormField";
import CustomButton from "@/components/CustomButton";
import axios from "axios";
import { useGlobalContext } from "@/context/GlobalProvider";

const SignIn = () => {
  const { setUser, setToken } = useGlobalContext();
  const [isSubmitting, setSubmitting] = useState(false);
  const [form, setForm] = useState({
    mobNo: "",
    password: "",
  });

  const submit = async () => {
    if (form.mobNo === "" || form.password === "") {
      Alert.alert("Error", "Please fill in all fields");
    }
    const data = {
      mobNo: form.mobNo,
      password: form.password,
    };
    setSubmitting(true);
    try {
      const response = await axios.post(
        "http://192.168.43.29:8080/patients/logIn",
        data
      );
      console.log("response data", response.data, ":");
      if (response.data != "") {
        await setUser(response.data);
        await setToken(form.mobNo,form.password);
        router.replace("/(tabs)");
      } else {
        Alert.alert("User not Found", "Please Create New Account");
      }
    } catch (error) {
      console.error("Error fetching data:", error);
    } finally {
      setSubmitting(false);
    }
  };

  return (
    <SafeAreaView className="bg-primaryBlack h-full">
      <ScrollView>
        <View
          className="w-full flex justify-center h-full px-4 my-6"
          style={{
            minHeight: Dimensions.get("window").height - 100,
          }}
        >
          <Text className="text-2xl font-semibold text-primaryLight  font-psemibold">
            Log in to DocBridge
          </Text>

          <FormField
            title="Mobile Number"
            value={form.mobNo}
            placeholder={"Enter Mobile Number"}
            handleChangeText={(e) => setForm({ ...form, mobNo: e })}
            otherStyles="mt-7"
            keyboardType="phone-pad"
          />

          <FormField
            title="Password"
            placeholder={"Enter Password"}
            value={form.password}
            handleChangeText={(e) => setForm({ ...form, password: e })}
            otherStyles="mt-7"
          />

          <CustomButton
            title="Sign In"
            handlePress={submit}
            containerStyles="mt-7"
            isLoading={isSubmitting}
            textStyles={""}
          />

          <View className="flex justify-center pt-5 flex-row gap-2">
            <Text className="text-lg text-gray-100 font-pregular">
              Don't have an account?
            </Text>
            <Link
              href="/(auth)/sign-up"
              className="text-lg font-psemibold text-primaryBlue"
            >
              Signup
            </Link>
          </View>
        </View>
      </ScrollView>
    </SafeAreaView>
  );
};

export default SignIn;
