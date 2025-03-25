import {
  View,
  Text,
  FlatList,
  Image,
  TouchableOpacity,
  Alert,
} from "react-native";
import { Link, router } from "expo-router";
import { Ionicons } from "@expo/vector-icons";
import CustomHeader from "@/components/CustomHeader";
import { SafeAreaView } from "react-native-safe-area-context";
import { useEffect, useState } from "react";
import { useGlobalContext } from "@/context/GlobalProvider";
import { doctorImages } from "../../constants/doctorImages";
import axios from "axios";

// Mock data for doctors
const Doctors = [
  {
    id: "1",
    name: "Dr. Sarah Johnson",
    specialty: "Cardiologist",
    experience: "15 years",
    rating: 4.8,
    image:
      "https://images.unsplash.com/photo-1559839734-2b71ea197ec2?q=80&w=300&h=300&fit=crop",
  },
  {
    id: "2",
    name: "Dr. Michael Chen",
    specialty: "Neurologist",
    experience: "12 years",
    rating: 4.7,
    image:
      "https://images.unsplash.com/photo-1612349317150-e413f6a5b16d?q=80&w=300&h=300&fit=crop",
  },
  {
    id: "3",
    name: "Dr. Emily Williams",
    specialty: "Pediatrician",
    experience: "10 years",
    rating: 4.9,
    image:
      "https://images.unsplash.com/photo-1594824476967-48c8b964273f?q=80&w=300&h=300&fit=crop",
  },
];

function DoctorCard({ doctor }) {
  return (
    <Link href={`/booking/${doctor["doctorId"]}`} asChild>
      <TouchableOpacity className="bg-secondaryBlack rounded-xl p-4 mb-4 flex-row shadow">
        <Image
          source={doctorImages[doctor["name"]]}
          className="w-20 h-20 rounded-full mr-4"
        />
        <View className="flex-1">
          <Text className="text-lg font-semibold text-primaryLight mb-1 font-pbold">
            {doctor.name}
          </Text>
          <Text className="text-sm text-slate-400 mb-2 font-pregular">
            {doctor.specialization}
          </Text>
          <View className="flex-row justify-between items-center">
            <Text className="text-sm text-slate-400 font-pregular">
              {doctor.experience} experience
            </Text>
            <View className="flex-row items-center">
              <Ionicons name="star" size={16} color="#fbbf24" />
              <Text className="ml-1 text-sm font-semibold text-slate-400 font-pregular">
                {doctor.rating}
              </Text>
            </View>
          </View>
        </View>
      </TouchableOpacity>
    </Link>
  );
}

export default function Home() {
  const { deleteToken } = useGlobalContext();
  const [doctors, setDoctors] = useState([]);

  const getDoctors = async () => {
    try {
      const response = await axios.get(
        "http://192.168.43.29:8080/doctors/list"
      );
      if (response.status === 404) {
        Alert.alert("Not Found", "No Doctors Found");
      }
      setDoctors(response.data);
    } catch (error) {
      console.error("Error fetching data:", error);
    }
  };

  const logOut = async () => {
    await deleteToken();
    router.replace("/(auth)/sign-in");
  };

  useEffect(() => {
    /* const asyncFunc = async () => {
      await deleteToken();
    };
    asyncFunc(); */
    getDoctors();
  }, []);
  return (
    <SafeAreaView className="flex-1 bg-primaryBlack">
      <View className="flex-row justify-between items-center mx-2">
        <CustomHeader title={"Find Your Doctor"} />
        <TouchableOpacity className="min-w-[80] rounded-xl bg-primaryBlue p-2" onPress={logOut}>
          <Text className="text-primaryLight text-lg font-psemibold">
            Log Out
          </Text>
        </TouchableOpacity>
      </View>
      <FlatList
        data={doctors}
        renderItem={({ item }) => <DoctorCard doctor={item} />}
        keyExtractor={(item) => item.doctorId}
        contentContainerClassName="p-4"
        ListEmptyComponent={
          <Text className="text-xl text-primaryLight text-center">
            {" "}
            No Doctors Found !
          </Text>
        }
      />
    </SafeAreaView>
  );
}
